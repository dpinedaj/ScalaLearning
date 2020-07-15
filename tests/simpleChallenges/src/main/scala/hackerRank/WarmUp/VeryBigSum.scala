package hackerRank.WarmUp

object VeryBigSum {
  def veryBigSum(ar: Array[Long]): Long = {
    ar.sum
  }

  def solution(): Unit = {
    println("Insert array values with blank space separation")
    val ar =io.StdIn.readLine.split(" ").map(_.trim.toLong)
    val result = veryBigSum(ar)

    println(result)
  }
}
