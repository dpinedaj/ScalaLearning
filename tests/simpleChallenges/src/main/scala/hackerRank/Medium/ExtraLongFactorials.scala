package hackerRank.Medium

object ExtraLongFactorials {
  def extraLongFactorials(n: Int): Long = {
    @scala.annotation.tailrec
    def iterFactorials(n: Int, value: Long): Long = {
      if (n == 0) value
      else {
        val v = if (value == 0) n else value * n
        iterFactorials(n - 1, v.toLong)
      }
    }

    iterFactorials(n, 0)
  }

  def solution() {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    val result = extraLongFactorials(n)
    println(result)
  }
}
