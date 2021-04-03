import scala.annotation.tailrec
def iterPascal(row: Int, column: Int): Int = {
  if (column == 1 || column == row || row == 1) 1
  else iterPascal(row - 1, column - 1) + iterPascal(row - 1, column)
}
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



pascal(10)