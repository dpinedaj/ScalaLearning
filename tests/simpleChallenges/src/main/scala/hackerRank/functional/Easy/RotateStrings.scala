package hackerRank.functional.Easy

import scala.annotation.tailrec

object RotateStrings {
  io.StdIn.readInt()

  def rotateFunc(word: String): Unit = {
    val len = word.length

    @tailrec
    def iter(pos: Int): Unit = {
      if (pos <= len) {
        print(word.substring(pos, len) + word.substring(0, pos) + " ")
        iter(pos + 1)
      }
    }

    iter(1)
    println()
  }
}
