package examples.module_2

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.DataFrame
import shapeless.Data

object SparkMissingValues {
  Logger.getLogger("org").setLevel(Level.OFF)

  val sc: SparkContext = new SparkContext("local[*]", "missingValues")
  val spark: SparkSession = SparkSession.builder().getOrCreate()
  import spark.implicits._

  def main(args: Array[String]): Unit = {

    try {
      val (df, dfnan) = define_df
      println("Initial Dataframe")
      df.show()
      println("\nDataFrame with NaN")
      dfnan.show()

      dropNa(dfnan)
      fillNa(dfnan)
      replaceNa(dfnan)
      duplicates(df, dfnan)
    } finally {
      this.sc.stop()
      this.spark.stop()
    }
  }
  def define_df: (DataFrame, DataFrame) = {
    val df = this.spark
      .range(0, 10)
      .select("id")
      .withColumn("uniform", rand(10L))
      .withColumn("normal", randn(10L))

    val halfTonNaN = udf[Double, Double](x => if (x > 0.5) Double.NaN else x)
    val oneToNaN = udf[Double, Double](x => if (x > 1.0) Double.NaN else x)

    val dfnan: DataFrame = df
      .withColumn("nanUniform", halfTonNaN(df("uniform")))
      .withColumn("nanNormal", oneToNaN(df("normal")))
      .drop("uniform")
      .withColumnRenamed("nanUniform", "uniform")
      .drop("normal")
      .withColumnRenamed("nanNoemal", "normal")

    return (df, dfnan)
  }

  def dropNa(dfnan: DataFrame): Unit = {
    println("\nDropping Rows With minNonNulls Argument")
    dfnan.na.drop(minNonNulls = 3).show()
    println("\nDropping Rows With How Argument---All")
    dfnan.na.drop("all", Array("uniform", "nanNormal")).show()
    println("\nDropping Rows With How Argument---Any")
    dfnan.na.drop("any", Array("uniform", "nanNormal")).show()
  }
  def fillNa(dfnan: DataFrame): Unit = {
    println("\nFilling Missing Data By Column Type")
    dfnan.na.fill(0.0).show()

    println("\nFilling Missing Data With Column Defaults")
    val uniformMean =
      dfnan.filter("uniform <> 'NaN'").groupBy().agg(mean("uniform")).first()(0)

    dfnan.na.fill(Map("uniform" -> uniformMean)).show(5)

    println("\nFilling Missing Data With Column Defaults")
    
    val dfnanCols = dfnan.columns.drop(1)

    val dfnanMeans = dfnan.na
      .drop()
      .groupBy()
      .agg(mean("uniform"), mean("nanNormal"))
      .first()
      .toSeq

    val meansMap = (dfnanCols.zip(dfnanMeans)).toMap
    dfnan.na.fill(meansMap).show(5)
  }
  def replaceNa(dfnan: DataFrame): Unit = {
    println("\nReplacing Values in a DataFrame by 0.0")
    dfnan.na.replace("uniform", Map(Double.NaN -> 0.0)).show()
  }

  def duplicates(df: DataFrame, dfnan: DataFrame): Unit = {
    println("\nDropping Duplicate Rows")
    val dfDuplicates =
      df.unionAll(this.sc.parallelize(Seq((10, 1, 1), (11, 1, 1))).toDF())

    val dfCols = dfnan.withColumnRenamed("nanNormal", "normal").columns

    dfDuplicates.dropDuplicates(dfCols).show()
  }
}
