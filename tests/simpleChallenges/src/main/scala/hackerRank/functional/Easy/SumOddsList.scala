package hackerRank.functional.Easy

object SumOddsList {
  def f(arr: List[Int]): Int = arr.reduce((x, y) => if (y % 2 != 0) x + y else x)
}
