package examples.module_3

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.DataFrame

//Transformer and estimator to perform PCA
import org.apache.spark.ml.feature.{VectorAssembler, PCA}

object PrincipalComponentAnalysis {
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark: SparkSession =
    SparkSession.builder().master("local[*]").getOrCreate()
  def main(args: Array[String]): Unit = {
    try {

      val df = loadData
      println("Initial DataFrame:")
      df.printSchema()

      println("\nDataFrame after PCA:")
      val dfPca = applyPCA(df)
      dfPca.show(truncate=false)
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

  def applyPCA(df: DataFrame): DataFrame = {
    val assembler = new VectorAssembler()
      .setInputCols(
        df.columns.filterNot(name =>
          List("community", "otherpercap").contains(name.toLowerCase)
        )
      )
      .setOutputCol("features")
    val featuresDf = assembler.transform(df).select("features")
    val pca = new PCA()
    .setInputCol("features")
    .setOutputCol("pcaFeatures")
    .setK(10)
    .fit(featuresDf)

    val result = pca.transform(featuresDf).select("pcaFeatures")
    return result
  }
}
