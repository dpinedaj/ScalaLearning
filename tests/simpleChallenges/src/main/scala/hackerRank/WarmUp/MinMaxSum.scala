package hackerRank.WarmUp

object MinMaxSum {

  def miniMaxSum(arr: Array[Int]) {
    println(Array(arr.sorted.dropRight(1).sum,
                  arr.sorted.drop(1).sum)
            .mkString(" "))

  }

  def solution() {
    val arr = io.StdIn.readLine.split(" ").map(_.trim.toInt)
    miniMaxSum(arr)
  }
}
