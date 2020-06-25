package examples.module_3

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

//Estimator to generate RFormula analysis
import org.apache.spark.ml.feature.RFormula

object RFormulas {
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark: SparkSession =
    SparkSession.builder().master("local[*]").getOrCreate()

  def main(args: Array[String]): Unit = {
    try {

      val df = loadData
      println("Initial DataFrame")
      df.printSchema()

      val dfR = evalRFormula(df)
      dfR.show(5)
    } finally {
      this.spark.stop()
    }
  }

  def loadData: DataFrame = {
    val crimes = spark.read
      .format("com.databricks.spark.csv")
      .option("delimiter", ",")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("data/UScrime2-colsLotsOfNAremoved.csv")
    return crimes
  }

  def evalRFormula(df: DataFrame): DataFrame = {
    /*RFormula allows you to apply formulas in R notation
        can be generated some pipelines and data transforms

     */
    val formula = new RFormula()
      .setFormula(
        "ViolentCrimesPerPop ~ householdsize + PctLess9thGrade + pctWWage"
      )
      .setFeaturesCol("features")
      .setLabelCol("label")
    val output = formula.fit(df).transform(df)
    return output
  }
}
