package examples.module_3

import org.apache.log4j.{Logger, Level}

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

//Transformer to generate vector assembler
import org.apache.spark.ml.feature.VectorAssembler

//Transformer to generate slices
import org.apache.spark.ml.feature.VectorSlicer

object FeatureVectors {
  Logger.getLogger("org").setLevel(Level.OFF)
  case class Customer(
      churn: Int,
      sessions: Int,
      revenue: Double,
      recency: Int
  )

  val spark: SparkSession =
    SparkSession.builder().master("local[*]").getOrCreate()
  import spark.implicits._

  def main(args: Array[String]): Unit = {

    try {
      val df = generateData
      println("Initial dataframe:")
      df.show()

      val dfWithFeatures = generateFeatureVectors(df)
      println("\nDataframe as FeatureVectors:")
      dfWithFeatures.show()

      val slicedDf = sliceData(dfWithFeatures)
      println("\nDataframe Sliced:")
      slicedDf.show()

    } finally {
      this.spark.close()
    }
  }

  def generateData: DataFrame = {

    val customers = {
      this.spark
        .createDataFrame(
          Seq(
            Customer(1, 20, 61.24, 103),
            Customer(1, 8, 80.64, 23),
            Customer(0, 4, 100.94, 42),
            Customer(1, 17, 120.56, 47)
          )
        )
        .toDF()
    }
    return customers
  }

  def generateFeatureVectors(df: DataFrame): DataFrame = {
    val assembler = new VectorAssembler()
      .setInputCols(Array("sessions", "revenue", "recency"))
      .setOutputCol("features")
    val dfWithFeatures = assembler.transform(df)
    return dfWithFeatures
  }

  def sliceData(df: DataFrame): DataFrame = {
    //Take just two features
    //Allows me to use just some features to analytics
    val slicer =
      new VectorSlicer().setInputCol("features").setOutputCol("some_features")

    val slicedDf = slicer.setIndices(Array(0, 1)).transform(df)

    return slicedDf

  }
}
