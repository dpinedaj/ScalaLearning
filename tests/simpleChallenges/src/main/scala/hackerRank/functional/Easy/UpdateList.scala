package hackerRank.functional.Easy

object UpdateList {
  def f(arr: List[Int]): List[Int] = arr.map(x => if (x < 0) -x else x)
}
