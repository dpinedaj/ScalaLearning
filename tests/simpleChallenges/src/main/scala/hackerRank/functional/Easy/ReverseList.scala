package hackerRank.functional.Easy

object ReverseList {
  def f(arr: List[Int]): List[Int] = (for (i <- arr.length - 1 to 0 by -1) yield arr(i)).toList
  def f2(arr: List[Int]): List[Int] = arr.reverse
}
