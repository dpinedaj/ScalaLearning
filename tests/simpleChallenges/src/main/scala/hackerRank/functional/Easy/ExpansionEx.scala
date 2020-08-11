package hackerRank.functional.Easy

object ExpansionEx {

  def factorial(i: Int): Int = {
    if (i == 1) 1
    else i * factorial(i - 1)
  }

  def expansion(i: Int, acc: Double, x: Double): Double = {
    if (i == 10) acc
    else acc + expansion(i + 1, math.pow(x, i) / factorial(i), x)
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    for (nItr <- 1 to n) {
      val x = stdin.readLine.trim.toDouble
      println(expansion(1, 1, x))
    }
  }

}
