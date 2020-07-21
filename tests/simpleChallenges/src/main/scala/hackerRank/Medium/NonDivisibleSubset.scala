package hackerRank.Medium

object NonDivisibleSubset {
  /*
       * Complete the 'nonDivisibleSubset' function below.
       *
       * The function is expected to return an INTEGER.
       * The function accepts following parameters:
       *  1. INTEGER k
       *  2. INTEGER_ARRAY s
       */

  def nonDivisibleSubset(k: Int, s: Array[Int]): Int = {
    def check(ar: Array[Int]): Int = {
      (for (i <- 1 until ar.length if (ar(0) + ar(i)) % k != 0) yield ar(i)).length
    }

    def checkMax(x: Int, y: Int): Int = if (x > y) x else y

    @scala.annotation.tailrec
    def generalCheck(max: Int, ar: Array[Int]): Int = {
      if (ar.isEmpty) max
      else generalCheck(checkMax(max, check(ar)), ar.tail)
    }

    generalCheck(0, s)
  }

  def main(args: Array[String]) {
    val stdin = io.StdIn
    val Array(n, k) = stdin.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val s = stdin.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val result = nonDivisibleSubset(k, s)
    println(result)

  }
}

