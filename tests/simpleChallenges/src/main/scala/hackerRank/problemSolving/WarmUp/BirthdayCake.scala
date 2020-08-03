package hackerRank.problemSolving.WarmUp

object BirthdayCake {
  def birthdayCakeCandles(ar: Array[Int]): Int = {
    ar.count(x => x == ar.max)
  }

  def solution() {
    val stdin = scala.io.StdIn
    val ar = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = birthdayCakeCandles(ar)
    println(result)
  }
}
