package hackerRank.functional.Easy

object FilterArray {
  def f(arr: List[Int]): List[Int] = {
    val n = arr.length
    val way1 = (for (i <- 0 until n if i % 2 != 0) yield arr(i)).toList
    val way2 = arr.zipWithIndex.filter(_._2 % 2 != 0).map(_._1)
    way1
  }
}
