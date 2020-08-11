package hackerRank.functional.Easy

object ListOfN {

  import scala.io.StdIn.readInt

  def f(num: Int): List[Int] = (0 until num).toList

  println(f(readInt))
}
