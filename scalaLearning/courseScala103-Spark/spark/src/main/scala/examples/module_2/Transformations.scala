package examples.module_2

import org.apache.spark.SparkContext
import java.io.File.separator

//Own modules
import utils._

object Transformations {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "Transformations")

    try {
      //crawl(sc)
      invertedIndex(sc)
    } finally {
      println("...")
      //Console.in.read()
      sc.stop()
    }
  }
  def crawl(sc: SparkContext): Unit = {
    val sep = separator // sep: String = /
    val inpath = "data/crawl/enron1/*"
    val outpath = "data/crawl/rawData"
    MyFiles.deleteFileOrFolder(outpath)
    /*wholeTextFiles allows me to read multiple text files in some directories
        and put the fileName as key and content as value*/
    val files_contents = sc.wholeTextFiles(inpath).map {
      case (id, text) =>
        val lastSep = id.lastIndexOf(sep) //Take just the fileName (without the path)
        val id2 =
          if (lastSep < 0) id.trim //remove side white spaces
          else id.substring(lastSep + 1, id.length).trim
        val text2 = text.trim.replaceAll("""\s*\n\s*""", " ")
        (id2, text2)
    }
    println(s"Writing output to $outpath")
    files_contents.saveAsTextFile(outpath)
    println("Done")
  }
  def invertedIndex(sc: SparkContext): Unit = {

    val inpath = "data/crawl/rawData/*"
    val outpath = "data/crawl/output"
    MyFiles.deleteFileOrFolder(outpath)

    val lineRE = """^\s*\(([^,]+),(.*)\)\s*$""".r //The ".r" method convert it to a regex
    val input = sc
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
      .reduceByKey { (count1, count2) => count1 + count2 }
      .map {
        case ((word, path), n) => (word, (path, n))
      }
      .groupByKey
      .map {
        case (word, iterable) => (word, iterable.mkString(", "))
      }
  }
}
