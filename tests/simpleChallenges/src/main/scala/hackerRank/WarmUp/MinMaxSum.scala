package hackerRank.WarmUp

object MinMaxSum {

  def miniMaxSum(arr: Array[Int]) {
    val arrS = arr.sorted
    val max = arrS.drop(1).sum
    val min = arr.dropRight(1).sum
    println(Array(min, max).mkString(" "))

  }

  def solution() {
    val stdin = scala.io.StdIn

    val arr = stdin.readLine.split(" ").map(_.trim.toInt)
    miniMaxSum(arr)
  }
}
