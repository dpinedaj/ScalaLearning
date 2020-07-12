package hackerRank.WarmUp

object ArraySum {

  /*
   Given an array of integers, find the sum of its elements.
    For example, if the array ar= [1,2,3], 1 +2 +3 = 6, so return 6
   */
  def simpleArraySum(ar: Array[Int]): Int = {
    ar.sum
  }

  def solution():Unit = {
    val stdin = scala.io.StdIn
    val ar = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = simpleArraySum(ar)
    println(result)
  }
}

