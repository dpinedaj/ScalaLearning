package utils

import java.io._
import scala.io.Source

object MyFiles {
  //Class made to manage directories and files in Scala
  case class FileOperationError(msg: String) extends RuntimeException(msg)

  def rmrf(root: String): Unit = rmrf(new File(root))

  def rmrf(root: File): Unit = {
    if (root.isFile) root.delete()
    else if (root.exists) {
      root.listFiles.foreach(rmrf)
      root.delete()
    }
  }
  def clearDirectory(pathDir: String): Unit = (new File(pathDir).listFiles.foreach(rmrf))

  def rm(file: String): Unit = rm(new File(file))

  def rm(file: File): Unit =
    if (file.delete == false)
      throw FileOperationError(s"Deleting $file failed!")

  def mkdir(path: String): Unit = (new File(path)).mkdirs

   
  def readFile(filePath: String): Seq[String] = {
    val bufferedSource = io.Source.fromFile(filePath)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }
}
