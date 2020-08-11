package hackerRank.functional.Easy

import scala.annotation.tailrec

object HelloWorldNTimes {
  def f(n: Int): Unit = {
    @tailrec
    def iter(i: Int): Unit = {
      if (i < n) {
        println("Hello World")
        iter(i + 1)
      }

    }

    iter(0)
  }

  var n: Int = scala.io.StdIn.readInt
  f(n)
}
