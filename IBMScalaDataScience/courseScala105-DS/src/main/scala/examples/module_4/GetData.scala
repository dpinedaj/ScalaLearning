package examples.module_4

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.SparkContext

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.util.MLUtils.{
  convertVectorColumnsFromML => fromML,
  convertVectorColumnsToML => toML
}
object GetData {
  Logger.getLogger("org").setLevel(Level.OFF)
  val sc: SparkContext = new SparkContext("local[*]", "GradientBoost")
  val spark: SparkSession = SparkSession.builder().getOrCreate()
  import spark.implicits._

  def main(args: Array[String]): Unit = {
    try {
      if (!(new java.io.File("data/sample_libsvm_data.txt").exists)) downloadData
      else println("The file already exists")
      val df = loadData
      df.show()
    } finally {
      this.sc.stop()
      this.spark.stop()
    }
  }

  def downloadData: Unit = {
    import sys.process._
"wget https://s3-api.us-geo.objectstorage." +
  "softlayer.net/cf-courses-data/CognitiveClass/" +
  "SC0105EN/data/sample_libsvm_data.txt  -P data/" !

  }
  def loadData: DataFrame = {
    val data = toML(
      MLUtils
        .loadLibSVMFile(this.sc, "data/sample_libsvm_data.txt")
        .toDF()
    )
    return data
  }
}
