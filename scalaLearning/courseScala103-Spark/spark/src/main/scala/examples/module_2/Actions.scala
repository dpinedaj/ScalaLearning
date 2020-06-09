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
    val outputFile: String = "output/tests/actions"

    MyFiles.rmrf(outputFile)
    try {
      val rdd = sc.parallelize(data)
      println(s"Num of partitions: ${rdd.partitions.size}")
      println(s"Num of registers ${rdd.count()}")

      //repartition data
      val rdd2 = rdd.coalesce(1)
      println(s"Num of partitions after repartition: ${rdd2.partitions.size}")

      rdd2.collect().foreach(println)

      rdd2.saveAsTextFile(outputFile)
      println("Written file")
    } finally {
      sc.stop()
    }
  }

}
