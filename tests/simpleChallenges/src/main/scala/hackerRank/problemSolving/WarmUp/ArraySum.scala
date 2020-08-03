package hackerRank.problemSolving.WarmUp

object ArraySum {

  /*
   Given an array of integers, find the sum of its elements.
    For example, if the array ar= [1,2,3], 1 +2 +3 = 6, so return 6
   */
  def simpleArraySum(ar: Array[Int]): Int = {
    ar.sum
  }

  def solution():Unit = {
    val ar = io.StdIn.readLine.split(" ").map(_.trim.toInt)
    val result = simpleArraySum(ar)
    println(result)
  }
}

