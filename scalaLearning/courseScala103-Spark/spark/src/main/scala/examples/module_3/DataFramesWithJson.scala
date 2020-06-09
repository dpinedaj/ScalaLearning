package examples.module_3

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.DataFrame

//Import own modules
import examples.data._
object DataFramesWithJson {

  val out = Console.out

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("Spark DataFrames")

    conf.set("spark.sql.shuffle.partitions", "4")
    conf.set("spark.app.id", "DataFrameWithJson") // To silence Metrics warning.

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    try {
      val inPath: String = "data/module_3_4/airline-flights/carriers.json"
      //each line should have a complete json record
      val json: DataFrame =
        sqlContext.read.format("json").load(inPath) //Can be .read.json(inPath)
      //spark infers the schema as it reads the json document. Since there is a invalid
      //json record the schema will have an additional column called _corrupt_record
      //for invalid json record.
      // It doesn't stop the processing when it finds an invalid records which is great for
      //large jobs as you don't want to stop for each invalid data record
      json.printSchema()
      Printer(out, "Load carrier information", json.sample(0.01))

      //Printing out the records that failed to parse
      json.where(json("_corrupt_record").isNotNull).collect().foreach(println)
    } finally {
      print("Finish")
      sc.stop()
    }
  }
}
