package hackerRank.Medium

object ExtraLongFactorials {
  def extraLongFactorials(n: Int): BigInt = {
    @scala.annotation.tailrec
    def iterFactorials(n: Int, value: BigInt): BigInt = {
      if (n == 0) value
      else {
        iterFactorials(n - 1, value * n)
      }
    }

    iterFactorials(n, 1)
  }

  def solution() {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    val result = extraLongFactorials(n)
    println(result)
  }
}
