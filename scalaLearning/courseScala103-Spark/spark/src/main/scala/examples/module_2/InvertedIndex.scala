package examples.module_2

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import java.io.File.separator
//Imports for broadcast stopWords
import org.apache.spark.broadcast.Broadcast

//Own modules
import utils._

object InvertedIndex {

  //Constructor--variables

  val inPath = "output/module_2/crawl/crawl"
  val outPath = "output/module_2/crawl/invertedIndex"
  val wordsOutPath = "output/module_2/crawl/words"
  val sc = new SparkContext("local[*]", "Transformations")
  val stopWords: Broadcast[Set[String]] =
    sc.broadcast(StopWords.words)

  def main(args: Array[String]): Unit = {

    val t0 = System.nanoTime()
    try {
      MyFiles.rmrf(this.outPath)
      MyFiles.rmrf(this.wordsOutPath)

      val (rdd, words) = this.invertedIndex(this.sc)

      println(s"Writing output to ${this.outPath}")

      rdd.saveAsTextFile(this.outPath)
      words.saveAsTextFile(this.wordsOutPath)

    } finally {
      println("...")
      sc.stop()

      val t1 = System.nanoTime()
      println("Elapsed time: %.2f seconds".format((t1 - t0) / 1e9d))
    }
  }

  def invertedIndex(sc: SparkContext): (RDD[(String, String)], RDD[String]) = {

    val lineRE = """^\s*\(([^,]+),(.*)\)\s*$""".r //The ".r" method convert it to a regex
    val rdd = sc
      .textFile(this.inPath)
      .map {
        case lineRE(name, text) => (name.trim, text.toLowerCase)
        case badLine =>
          Console.err.println(s"Unexpected line: $badLine")
          ("", "")
      }
      .flatMap {
        case (path, text) =>
          text.trim
            .split("""[^\w']""")
            .map(word => ((word, path), 1)) // every single word count 1 time in each time it appears
      }
      .filter {
        case ((word, _), _) => stopWords.value.contains(word) == false
      }
      .reduceByKey { (count1, count2) =>
        count1 + count2 //Sum all ocurrencies of every key
      }
      .map {
        case ((word, path), n) =>
          (
            word,
            (path, n)
          ) //Order the data in word as key and (path,n) as value
      }
      .groupByKey //Group the values by the word (key)
      .sortBy(
        x => x._2.map(y => y._2),
        ascending = false
      ) //Order the data acending by the most used words
      .map {
        case (word, iterable) =>
          (word, iterable.mkString(", ")) //Convert iterable as string to write
      }

      val words = rdd.map(x => (x._1))
    return (rdd, words)

  }
}
