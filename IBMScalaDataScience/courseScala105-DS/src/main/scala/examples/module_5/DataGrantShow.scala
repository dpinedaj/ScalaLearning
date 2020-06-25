package examples.module_5

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import java.io.File
object DataGrantShow {
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = SparkSession.builder().master("local[*]").getOrCreate()
  import spark.implicits._

  val df = loadData

  def main(args: Array[String]): Unit = {
    try {
      if (!(new File("data/grantsPeople.csv").exists)) downloadData
      else println("The file already exists")
      this.df.show()
      println("Schema:")
      df.printSchema()
    } finally {
      this.spark.stop()
    }
  }

  def downloadData: Unit = {
    import sys.process._
    "wget https://s3-api.us-geo.objectstorage." +
      "softlayer.net/cf-courses-data/CognitiveClass/" +
      "SC0105EN/data/grantsPeople.csv  -P data/" !

  }
  def loadData: DataFrame = {
    val data = spark.read
      .format("com.databricks.spark.csv")
      .option("delimiter", "\t")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("data/grantsPeople.csv")
    return data
  }
}
