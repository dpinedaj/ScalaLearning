package examples.module_2

import org.apache.spark.SparkContext

//import own modules
import utils._

object Actions {
  /*  Some actions:
        -count
        -collect
        -foreach
        -saveAsTextFile*/
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[*]", "Actions")
    val data = (1 to 10).map(i => (s"row$i", (i+96).toChar.toString)) toSeq
    val outputFile: String = "data/tests/actions"

    MyFiles.deleteFileOrFolder(outputFile)
    try {
      val rdd = sc.parallelize(data)
      println(rdd.count())
      rdd.collect().foreach(println)
      
      rdd.saveAsTextFile(outputFile)
      println("Written file")
    } finally {
      sc.stop()
    }
  }

}
