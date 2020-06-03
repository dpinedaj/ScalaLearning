package examples.module_2

import org.apache.spark.SparkContext
import java.io.File.separator

//Own modules
import utils._

object InvertedIndex {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[*]", "Transformations")

    try {
      this.invertedIndex(sc)
    } finally {
      println("...")
      sc.stop()
    }
  }

  def invertedIndex(sc: SparkContext): Unit = {

    val inpath = "data/crawl/output"
    val outpath = "data/crawl/invertedIndex"
    MyFiles.deleteFileOrFolder(outpath)

    val lineRE = """^\s*\(([^,]+),(.*)\)\s*$""".r //The ".r" method convert it to a regex
    val rdd = sc
      .textFile(inpath)
      .map {
        case lineRE(name, text) => (name.trim, text.toLowerCase)
        case badLine =>
          Console.err.println(s"Unexpected line: $badLine")
          ("", "")
      }
      .flatMap {
        case (path, text) =>
          text.trim.split("""[^\w']""").map(word => ((word, path), 1))
      }
      .reduceByKey { (count1, count2) =>
        count1 + count2
      } //Sum all ocurrencies of every key
      .map {
        case ((word, path), n) => (word, (path, n))
      }
      .groupByKey
      .map {
        case (word, iterable) => (word, iterable.mkString(", "))
      }
    println(s"Writing output to $outpath")

    rdd.saveAsTextFile(outpath)

  }

}
