package hackerRank.Easy

object SherlockSquares {
  def squares(a: Int, b: Int): Int = {
    @scala.annotation.tailrec
    def sqIter(n: Int, c: Int): Int = {
      if (c*c == n ) 1
      else if (c > n/2) 0
      else sqIter(n, c+1)
    }
    val result = (for(i<-a to b) yield sqIter(i, 0)).sum
    result
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn
    val q = stdin.readLine.trim.toInt
    for (qItr <- 1 to q) {
      val Array(a, b) = stdin.readLine.split(" ").map(_.trim.toInt)
      val result = squares(a, b)
      println(result)
    }

  }

}
