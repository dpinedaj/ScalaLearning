package examples.module_3

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.UserDefinedFunction

object ExplodeUdfPivot {
  Logger.getLogger("org").setLevel(Level.OFF)
  case class Sales(
      id: Int,
      account: String,
      year: String,
      commission: Int,
      sales_reps: Seq[String]
  )

  val spark: SparkSession =
    SparkSession.builder().master("local[*]").getOrCreate()

  import spark.implicits._
  def main(args: Array[String]): Unit = {
    try {
      val df = generateData
      println("Initial DataFrame:")
      df.show()

      val dfExplo = explodeFun(df)
      println("\nExploded DataFrame:")
      dfExplo.show()

      val userFun = udfFun

      val dfExploUdf = explodeFunWithUdf(df, userFun)
      println("\nExploded DataFrame with UDF:")
      dfExploUdf.show()

      val (dfG1, dfG2) = pivotFun(dfExploUdf)
      println("\nGrouped1:")
      dfG1.show()
      println("\nGrouped2:")
      dfG2.show()

    } finally {
      this.spark.stop()
    }
  }
  def generateData: DataFrame = {
    val sales = spark.createDataFrame(
      Seq(
        Sales(1, "Acme", "2013", 1000, Seq("Jim", "Tom")),
        Sales(2, "Lumos", "2013", 1100, Seq("Fred", "Ann")),
        Sales(3, "Acme", "2014", 2800, Seq("Jim")),
        Sales(4, "Lumos", "2014", 4200, Seq("Fred", "Sally"))
      )
    )
    return sales
  }

  def explodeFun(df: DataFrame): DataFrame = {
    df.select(
      $"id",
      $"account",
      $"year",
      $"commission",
      explode($"sales_reps") as ("sales_rep")
    )
  }
  def udfFun: UserDefinedFunction = {
    val len: (Seq[String] => Int) = (arg: Seq[String]) => { arg.length }
    val column_len = udf(len)
    return column_len
  }
  def explodeFunWithUdf(df: DataFrame, fun: UserDefinedFunction): DataFrame = {
    df.select(
      $"id",
      $"account",
      $"year",
      $"commission",
      ($"commission" / fun($"sales_reps")).as("share"),
      explode($"sales_reps").as("sales_rep")
    )
  }
  def pivotFun(df: DataFrame): (DataFrame, DataFrame) = {

    val dfG1 = df
      .groupBy($"sales_rep")
      .pivot("year")
      .agg(sum("share"))
      .orderBy("sales_rep")
    val dfG2 = df
      .groupBy($"account", $"sales_rep")
      .pivot("year")
      .agg(sum("share"))
      .orderBy("account", "sales_rep")

    return (dfG1, dfG2)

  }
}
