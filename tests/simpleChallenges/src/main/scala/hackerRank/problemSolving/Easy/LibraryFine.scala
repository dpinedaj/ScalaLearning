package hackerRank.problemSolving.Easy

object LibraryFine {
  def libraryFine(d1: Int, m1: Int, y1: Int, d2: Int, m2: Int, y2: Int): Int = {
    if (y1 > y2) 10000
    else if (y1 == y2 && m1 > m2) (m1 - m2) * 500
    else if (y1 == y2 && m1 == m2 && d1 > d2) (d1 - d2) * 15
    else 0
  }
  def solution() {
    val stdin = scala.io.StdIn
    val Array(d1, m1, y1) = stdin.readLine.split(" ").map(_.trim.toInt)
    val Array(d2, m2, y2) = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = libraryFine(d1, m1, y1, d2, m2, y2)
    println(result)

  }
}
