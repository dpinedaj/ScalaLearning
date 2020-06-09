package examples

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.sql.functions._
import shapeless.Data

object ExamplesModule4 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("Spark DataFrames")
    conf.set("spark.sql.shuffle.partitions", "4")
    conf.set("spark.app.id", "Tests")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    import sqlContext.sql

    try {
      val df = sqlContext.read
      .option("inferSchema", "true")
      .option("header", "true")
      .csv("data/module_3_4/airline-flights/airports.csv")
    //"iata","airport","city","state","country","lat","long"

      aggTests(df).show
      cubeTests(df).show
    } finally {
      sc.stop()
    }
  }

  def aggTests(df: DataFrame): DataFrame = {
    
    val df2 = df.agg(
      max("lat") as "max",
      min("lat").as("min"),
      avg("lat") as "avg"
    )
            //+----------+--------+------------------+
            //|       max|     min|               avg|
            //+----------+--------+------------------+
            //|71.2854475|7.367222|40.036523625524204|
            //+----------+--------+------------------+
    return df2
  }

  def cubeTests(df: DataFrame): DataFrame = {
    val df2 = df.cube("state", "country").max("lat")
                //+-----+-------+-----------+
                //|state|country|   max(lat)|
                //+-----+-------+-----------+
                //|   AL|    USA|34.85645028|
                //|   MO|    USA|40.44725889|
                //|   ND|    USA|48.99778194|
                //|   TN|    USA|36.62188083|
                //|   AR|    USA|36.40423139|
                //|   ID|    USA|48.72632639|
                //|   CT|    USA|41.93887417|
                //|   NH|    USA|44.57537278|
                //|   MD|    USA|39.70794444|
                //|   DE|    USA|39.67872222|
                //|   PR|    USA|18.49486111|
                //|   NA|    USA|  48.415769|
                //|   GU|    USA|   13.48345|
                //|   CO|   null| 40.6152625|
                //|   NY|   null|44.93582722|
                //|   MN|   null|48.94138889|
                //|   GA|   null|34.85508722|
                //|   DC|   null|38.86872333|
                //|   ME|   null|47.28550417|
                //|   SD|   null|45.91869722|
                //+-----+-------+-----------+
    return df2
  }

  
}
