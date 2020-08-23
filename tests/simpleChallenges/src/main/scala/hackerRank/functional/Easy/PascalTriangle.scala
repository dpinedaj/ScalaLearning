package hackerRank.functional.Easy

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

  Solution(5)

  def Solution(k: Int): Unit = {
    def pascal(k: Int): Unit = {
      for (row <- 1 to k) {
        for (column <- 1 to row) {
          print(s"${iterPascal(row, column)} ")
        }
        println()
      }
    }

    def iterPascal(row: Int, column: Int): Int = {
      if (column == 0) 0
      else if (column == 1 || column == row || row == 1) 1
      else iterPascal(row - 1, column - 1) + iterPascal(row - 1, column)
    }

    pascal(k)
  }
}
