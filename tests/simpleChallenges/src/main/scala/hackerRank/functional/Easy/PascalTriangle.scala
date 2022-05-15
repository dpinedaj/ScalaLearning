package hackerRank.functional.Easy

import scala.annotation.tailrec
import scala.collection.mutable.{Map => MutableMap}

case class Block(row: Int, column: Int)

object PascalTriangle{
  /*
    This solution allows to perform the Pascal Triangle.
    i.e for a defined K = 5
    the oputput is:
        1
        1 1
        1 2 1
        1 3 3 1
        1 4 6 4 1
   */
  val memoizeMap: MutableMap[Block, Int] = MutableMap.empty[Block, Int]
  val initialRow: Int = 1
  val initialColumn: Int = 1

  def main(args: Array[String]): Unit = {
    pascal(30)
  }

  def pascal(lastRow: Int): Unit = {
    pascal(lastRow, initialRow, initialColumn)
  }

  @tailrec
  private def pascal(lastRow: Int, row: Int, column: Int): Unit = {
    if (row <= lastRow) {
      if (column <= row) {
        print(s"${iterPascal(Block(row, column))} ")
        pascal(lastRow, row, column + 1)
      } else {
        println()
        pascal(lastRow, row + 1, 1)
      }
    }
  }
  private def iterPascal(block: Block): Int = {
    if (block.column == 1 || block.column == block.row || block.row == 1) 1
    else if (memoizeMap.contains(block)) memoizeMap(block)
    else {
      val upperLeftKey = Block(block.row - 1, block.column - 1)
      val upperRightKey = Block(block.row - 1, block.column)
      val upperLeft = iterPascal(upperLeftKey)
      val upperRight = iterPascal(upperRightKey)
      memoizeMap(upperLeftKey) = upperLeft
      memoizeMap(upperRightKey) = upperRight
      upperLeft + upperRight
    }
  }
}
