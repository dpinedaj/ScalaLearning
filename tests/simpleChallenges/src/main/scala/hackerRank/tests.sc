
val s = Array(1,7,2,4)
val k = 3


def nonDivisibleSubset(k: Int, s: Array[Int]): Int = {
  def check(x: Int, ar: Array[Int]): Int = {
    (for (i <- 1 until ar.length if (x + ar(i)) % k != 0) yield ar(i)).length
  }

  def checkMax(x: Int, y: Int): Int = if (x > y) x else y

  @scala.annotation.tailrec
  def generalCheck(max: Int, ar: Array[Int]): Int = {
    if (ar.isEmpty) max
    else generalCheck(checkMax(max, check(ar(0), s)),ar.tail)
  }

  generalCheck(0, s)
}

nonDivisibleSubset(k, s)














































































































