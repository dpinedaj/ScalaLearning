package hackerRank.functional.Easy

import scala.annotation.tailrec

object ReduceStrings {
  @tailrec
  def iter(s: String, res: String=""):String = {
    if (!s.isEmpty) {
      if (res.contains(s.head)) iter(s.tail, res)
      else iter(s.tail, res + s.head)
    }
    else{
      res
    }
  }
}
