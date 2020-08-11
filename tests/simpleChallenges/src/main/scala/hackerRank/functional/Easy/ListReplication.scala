package hackerRank.functional.Easy

object ListReplication {
  def f(n: Int, l: List[Int]): List[Int] = {
    //3 different ways
    for (i <- l if i < n) yield i

    l.flatMap(x => if (x < n) Some(x) else None)

    l.filter(_ < n)
  }
}
