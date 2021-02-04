package hackerRank.functional.Easy

import scala.annotation.tailrec

object PascalTriangle extends App {
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

  pascal(5)

  @tailrec
  def pascal(k: Int, row: Int = 1, column: Int = 1): Unit = {
    if (row <= k){
      if (column <= row) {
        print(s"${iterPascal(row, column)} ")
        pascal(k, row, column + 1)
      }
      else{
        println()
        pascal(k, row+1, 1)
      }
    }

  }

  def iterPascal(row: Int, column: Int): Int = {
    if (column == 1 || column == row || row == 1) 1
    else iterPascal(row - 1, column - 1) + iterPascal(row - 1, column)
  }

}
