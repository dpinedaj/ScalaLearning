package examples.module_1

import scala.reflect.io.Directory //module to manage local directory or folders
import java.io.File //module to manage local files
import org.apache.spark.SparkContext //SparkContext class
import java.util.logging.{Level, Logger} //To specify the logger level

object WordCount {
  Logger.getLogger("org").setLevel(Level.OFF)
  def main(args: Array[String]): Unit = {
    val t0 = System.nanoTime()
    //define paths
    val inpath = "data/module_1/shakespare.txt"
    val outpath = "output/module_1/word_count1"
    //Init the sparkContext
    val sc = new SparkContext("local[*]", "Word Count")

    //remove old directory
    val directory = new Directory(new File(outpath))
    directory.deleteRecursively()

    try {
      //read input file as a textFile, into an RDD
      val input = sc.textFile(inpath)

      val wc = input
        .map(_.toLowerCase) //convert every single letter in lowercase
        .flatMap(text => text.split("""\W+""")) //split the text into words and flatt everything in a Seq
        .groupBy(word => word) //groupby every word--> it generates tuples2 with the key and a compactbuffer with the same word many times
        .mapValues(value => value.size) //.map(group => (group._1, group._2.size)) //change every value of the map(groups of tuples) for his size
        .filter(x => x._1.length() > 4) //remove "", " ", "and" and some short words

      val max = wc.sortBy(_._2, ascending = false).first() //Sort the list and take the first value

      println(
        "The most used word is: %s with %d occurrences".format(max._1, max._2)
      )
      println(s"Writting output to: $outpath")
      wc.saveAsTextFile(path = outpath)
      println("Finished")

      val t1 = System.nanoTime()

      println("Elapsed time: %.2f seconds".format((t1 - t0) / 1e9d))
    } finally {
      sc.stop()
    }
  }
}
