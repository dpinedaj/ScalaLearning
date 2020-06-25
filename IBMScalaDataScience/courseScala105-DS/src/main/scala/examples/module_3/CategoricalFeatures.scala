package examples.module_3

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

//Estimator to generate index for a string (one hot encoding)
import org.apache.spark.ml.feature.StringIndexer

//Transformer to get strings from index
import org.apache.spark.ml.feature.IndexToString

//Transformer to generate vector with the onehotencoder
import  org.apache.spark.ml.feature.OneHotEncoder

object CategoricalFeatures {
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark: SparkSession =
    SparkSession.builder().master("local[*]").getOrCreate()
  import spark.implicits._
  def main(args: Array[String]): Unit = {

    try {
      val df = generateData
      println("Initial Dataframe:")
      df.show()

      val dfIndexed = mapStringIndexer(df)
      println("\nIndexed DataFrame with String Indexer: ")
      dfIndexed.show()

      val dfInversed = mapIndexToString(dfIndexed)
      println("\nDataFrame with string from index:")
      dfInversed.show()

      val dfOneHot = oneHotEncoder(dfIndexed)
      println("\nDataFrame using OneHotEnconding:")
      dfOneHot.show()
    } finally {
      this.spark.stop()
    }
  }

  def generateData: DataFrame = {
    val df = spark
      .createDataFrame(
        Seq((0, "US"), (1, "UK"), (2, "FR"), (3, "CO"), (4, "US"), (5, "FR"))
      )
      .toDF("id", "nationality")
    return df
  }
  def mapStringIndexer(df: DataFrame): DataFrame = {
    val indexer: StringIndexer =
      new StringIndexer().setInputCol("nationality").setOutputCol("nIndex")
    val indexed: DataFrame = indexer.fit(df).transform(df)
    return indexed
  }

  def mapIndexToString(df: DataFrame): DataFrame = {
    val converter = new IndexToString()
      .setInputCol("predictedIndex")
      .setOutputCol("predictedNationality")
    val predictions = df.selectExpr("nIndex as predictedIndex")
    return converter.transform(predictions)
  }

  def oneHotEncoder(df: DataFrame): DataFrame = {
val encoder = new OneHotEncoder().setInputCol("nIndex").setOutputCol("nVector")
val  encoded = encoder.transform(df)
return encoded
  }
}
