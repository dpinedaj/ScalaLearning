package hackerRank.Easy

object JumpingClouds {
  def jumpingOnClouds(c: Array[Int], n: Int, k: Int): Int = {
    @scala.annotation.tailrec
    def iterJump(c: Array[Int], e: Int, pos: Int): Int = {
      if (n % k == 0) {
        if (c.isEmpty) e
        else {
          val nE = e - 1 - 2 * c(0)
          iterJump(c.drop(k), nE, 0)
        }
      }
      else {
        if (pos != 0 && pos % n == 0) e
        else {
          val p = if (pos > 10) pos - 10 else pos
          val nE = e - 1 - 2 * c(p)
          iterJump(c, nE, p + k)
        }
      }
    }

    iterJump(c, 100, 0)
  }

  def solution(): Unit = {
    val stdin = scala.io.StdIn
    val nk = stdin.readLine.split(" ")
    val n = nk(0).trim.toInt
    val k = nk(1).trim.toInt
    val c = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = jumpingOnClouds(c, n, k)
    println(result)
  }
}
