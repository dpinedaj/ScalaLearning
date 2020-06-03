package examples.module_2

import org.apache.spark.SparkContext
import java.io.File.separator

//Own modules
import utils._

object Crawl {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "Transformations")

    try {
      this.crawl(sc)
    } finally {
      println("...")
      sc.stop()
    }
  }
  
  def crawl(sc: SparkContext): Unit = {
    val sep = separator // sep: String = /
    val inpath = "data/crawl/enron1/*"
    val outpath = "data/crawl/output"
    MyFiles.deleteFileOrFolder(outpath)
    /*wholeTextFiles allows me to read multiple text files in some directories
        and put the fileName as key and content as value*/
    val files_contents = sc.wholeTextFiles(inpath).map {
      case (id, text) =>
        val lastSep = id.lastIndexOf(sep) //Take just the fileName (without the path)
        val id2 =
          if (lastSep < 0) id.trim //remove side white spaces
          else id.substring(lastSep + 1, id.length).trim
        val text2 = text.trim.replaceAll("""\s*\n\s*""", " ") //Remove all new lines
        (id2, text2)
    }
    println(s"Writing output to $outpath")
    files_contents.saveAsTextFile(outpath)
    println("Done")
  }
}
