package examples.module_2

import org.apache.spark.SparkContext

//For spark sql catalyst
import org.apache.spark.sql.SparkSession

//imports to improve queries and filtering
import org.apache.spark.sql.functions._

//Spark types
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.DataFrameStatFunctions

//To set desired logs
import org.apache.log4j.Logger
import org.apache.log4j.Level

object SparkDataFramesStatistics {
  Logger.getLogger("org").setLevel(Level.OFF)

  //Case class record for summary statistics
  case class Record(desc: String, value1: Int, value2: Double)

  val sc: SparkContext = new SparkContext("local[*]", "DataFrameStatistics")
  val spark: SparkSession = SparkSession.builder().getOrCreate()

  import spark.implicits._

  def main(args: Array[String]): Unit = {

    try {
      summaryStatistics(this.sc, this.spark)
      samplingDataFrames(this.spark)
      randomData(this.spark)
    } finally {
      this.sc.stop()
      this.spark.stop()
    }
  }

  def summaryStatistics(sc: SparkContext, spark: SparkSession): Unit = {

    def fetchStddev(dfStats: DataFrame): Unit = {
      //This method will show just the standard deviation from the statistics summary
      //The "$" method can be used for the spark.implicits._
      dfStats.filter($"summary" === "stddev").first
      val ar1 = dfStats
        .filter($"summary" === "stddev")
        .first
        .toSeq
        .drop(2)
        .map(_.toString.toDouble)
        .toArray

      println("\nStandard deviation\n\tvalue1\t\tvalue2")
      println(ar1.mkString(", "))
    }
    def fetchMaxMin(recDf: DataFrame): Unit = {
      //min and max values using a Map
      println("\nmin and max using Map groupby")
      recDf.groupBy().agg(Map("value1" -> "min", "value2" -> "min")).show()
      recDf.groupBy().agg(Map("value1" -> "max", "value2" -> "max")).show()

      println("\nMin and max using sql functions and grouped dataframe")
      //Min and max using functions
      val recStatsGroup: DataFrame =
        recDf.groupBy().agg(min("value1"), min("value2"))

      println(
        "columns of the grouped df: " + recStatsGroup.columns.mkString(", ")
      )
      println(
        "grouped values: " + recStatsGroup
          .first()
          .toSeq
          .toArray
          .map(_.toString.toDouble)
          .mkString(", ")
      )
    }
    def corrDataFrame(recDf: DataFrame): Unit = {
      val recDfStat: DataFrameStatFunctions = recDf.stat
      println(
        "Correlation value1 and value2: " + recDfStat.corr("value1", "value2")
      )
      println(
        "Covariance value1 and value2: " + recDfStat.cov("value1", "value2")
      )
      println("\nFreqItems")
      recDfStat.freqItems(Seq("value1"), 0.3).show

    }

    val records: Array[Record] = Array(
      Record("first", 1, 3.7),
      Record("second", -2, 2.1),
      Record("third", 6, 0.7)
    )

    val recRDD: RDD[Record] = sc.parallelize(records)
    val recDf: DataFrame = spark.createDataFrame(recRDD)
    //Shows the first 20 rows by default or can be specified into the brackets
    recDf.show()

    val recStats = recDf.describe()
    //Shows the count, mean, stddev, min and max of every field in the dataframe
    recStats.show()

    //Execute statistics
    fetchStddev(recStats)
    fetchMaxMin(recDf)
    corrDataFrame(recDf)

  }

  def samplingDataFrames(spark: SparkSession): Unit = {
    def simpleSample(
        df: DataFrame,
        withReplacement: Boolean,
        fraction: Double,
        seed: Long
    ): Unit = {
      val dfSampled = df.sample(
        withReplacement = withReplacement,
        fraction = fraction,
        seed = seed
      )
    }
    def randomSplit(df: DataFrame, weights: Array[Double], seed: Long): Unit = {
      println(s"\nRandom Split with weights ${weights.mkString(", ")}")
      val dfSplit: Array[DataFrame] =
        df.randomSplit(weights = weights, seed = seed)
      dfSplit(0).show()
      dfSplit(1).show()
    }
    def stratifiedSampling(
        df: DataFrame,
        col: String,
        fractions: Map[Int, Double],
        seed: Long
    ): Unit = {
      println(
        s"\nStratified Sampling with fractions: ${fractions.mkString(", ")}"
      )
      df.stat
        .sampleBy(col = col, fractions = fractions, seed = seed)
        .show()
    }
    val data: Seq[(Int, Int)] = Seq(
      (1, 10),
      (1, 20),
      (2, 10),
      (2, 20),
      (2, 30),
      (3, 20),
      (3, 30)
    )
    val df: DataFrame = spark.createDataFrame(data).toDF("key", "value")

    simpleSample(df, false, 0.3, 11L)
    randomSplit(df, Array(0.3, 0.7), 11L)
    stratifiedSampling(df, "key", Map(1 -> 0.7, 2 -> 0.7, 3 -> 0.7), 11L)
  }

  def randomData(spark: SparkSession): Unit = {
    val df = spark.range(0, 10)

    df.select("id")
      .withColumn("uniform", rand(10L))
      .withColumn("normal", randn(10L))
      .show()
  }
}
