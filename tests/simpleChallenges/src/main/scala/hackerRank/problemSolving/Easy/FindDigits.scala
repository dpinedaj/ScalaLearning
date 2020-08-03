package hackerRank.problemSolving.Easy

object FindDigits {

  def findDigits(n: Int): Int = {
    def and(x: Boolean, y: => Boolean): Boolean = if (x) y else false

    val nA = n.toString.split("").map(_.toInt)
    nA.count(x => and(x != 0, n % x == 0))
  }


  def main(args: Array[String]) {
    val stdin = scala.io.StdIn
    val t = stdin.readLine.trim.toInt
    for (tItr <- 1 to t) {
      val n = stdin.readLine.trim.toInt
      val result = findDigits(n)
      println(result)
    }


  }
}
