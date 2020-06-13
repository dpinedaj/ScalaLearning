package examples.module_3

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

//own modules
import utils._
import examples.data._

object DataFramesWithCsv {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("Spark DataFrames")
    // Change to a more reasonable default number of partitions for our data
    // (from 200)
    conf.set("spark.sql.shuffle.partitions", "4")
    conf.set("spark.app.id", "DataFrameWithCsv") // To silence Metrics warning.
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    try {
      val df = sqlContext.read
      //Specifies the input data source format. The readers and writers
      //of this format is provided by the databricks-csv library. This also shows
      //how to add support for custom data sources.
        .format("com.databricks.spark.csv")
        .option("header", "true") // Use first line of all files as header
        .option("inferSchema", "true") // Automatically infer data types
        .load("data/module_3_4/airline-flights/airports.csv")

      df.printSchema()
      df.show()

    } finally {
      println("Ready")
      sc.stop()
    }

  }

}
