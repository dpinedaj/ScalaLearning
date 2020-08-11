package hackerRank.functional.Easy

import scala.annotation.tailrec

object ListLength {
  @tailrec
  def f(arr: List[Int], n: Int): Int = {
    if (arr.isEmpty) n
    else f(arr.tail, n + 1)
  }

  def f2(arr: List[Int]): Int = {
    (for (i <- arr) yield 1).sum
  }
}
