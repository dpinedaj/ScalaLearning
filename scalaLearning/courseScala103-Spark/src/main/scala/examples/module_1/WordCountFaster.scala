package examples.module_1

//Imports
import org.apache.spark.{SparkContext, SparkConf} //SparkContext class
import java.util.logging.{Level, Logger} //To speciffy the logger level

//own modules
import utils.MyFiles

object WordCountFaster {
  Logger.getLogger("org").setLevel(Level.OFF)
  def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime()
    //define paths
    val inPath = "data/module_1/shakespare.txt"
    val outPath = "output/module_1/word_count2"
    val logsPath = "logs"

    //clear previous logs
    MyFiles.clearDirectory(logsPath)

    //Set sparkContext configuration
    val conf = new SparkConf()
      .set("spark.eventLog.enabled", "true")
      .set("spark.eventLog.dir", logsPath)

    //Init the sparkContext
    val sc = new SparkContext("local[*]", "Word Count", conf = conf)

    //remove old directory
    MyFiles.rmrf(outPath)

    try {
      //read input file as a textFile, into an RDD
      val input = sc.textFile(inPath)

      val wc = input
        .map(_.toLowerCase) //convert every single letter in lowercase
        .flatMap(text => text.split("""\W+""")) //split the text into words and flatt everything in a Seq
        .map(word => (word, 1)) // RDD[(String, Int)]
        .reduceByKey((n1, n2) => n1 + n2) // or .reduceByKey(_ + _)
        .filter(x => x._1.length() > 4) //remove "", " ", "and" and some short words

      val max = wc.sortBy(_._2, ascending=false).first() //Sort the list and take the first value

      println(
        "The most used word is: %s with %d occurrences".format(max._1, max._2)
      )
      println(s"Writing output to: $outPath")
      wc.saveAsTextFile(path = outPath)
      println("Finished")

      val t1 = System.nanoTime()

      println("Elapsed time: %.2f seconds".format((t1 - t0) / 1e9d))
    } finally {
      println("...")
      sc.stop()
    }
  }
}
