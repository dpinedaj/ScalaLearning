package examples.module_2

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
object Actions {
  /*  Some actions:
        -count
        -collect
        -foreach
        -saveAsTextFile*/
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[*]", "Actions")
    val data = Seq((1 to 10).map(i => (s"row$i", (i*18).toChar.toString)))

    try {
      val rdd = sc.parallelize(data)
      rdd.collect().foreach(println)

    } finally {
      sc.stop()
    }
  }

}
