package hackerRank.functional.Easy

object StringMingling extends App {
  """
    | reference: https://www.hackerrank.com/challenges/string-mingling/problem
    |""".stripMargin
  mingle_strings()
  def mingle_strings(): Unit = {
    val a = "abcde"
    val b = "pqrst"
    println(a.toSeq.zip(b.toSeq).flatten {case (a, b) => List(a, b) }.mkString(""))

  }
}