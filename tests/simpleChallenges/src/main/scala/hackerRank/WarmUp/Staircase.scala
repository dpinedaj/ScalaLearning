package hackerRank.WarmUp

object Staircase {
  def staircase(n: Int): Unit = {
    val range: Range = n - 1 to 0 by -1
    for (x <- range) println(" " * x + "#" * (n - x))

  }

  def solution(): Unit = {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    staircase(n)
  }
}
