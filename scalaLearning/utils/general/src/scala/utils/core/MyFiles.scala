package utils.core
import java.io._

object MyFiles {
  //Class made to manage directories and files in Scala

  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory) {
      file.listFiles.foreach(deleteRecursively)
    }
    if (file.exists && !file.delete) {
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
    }
  }
  def clearDirectory(pathDir: String): Unit = {
    val d = new File(pathDir)
    val files = d.listFiles
    for (file <- files) {
      deleteRecursively(file)
    }

  }
}
