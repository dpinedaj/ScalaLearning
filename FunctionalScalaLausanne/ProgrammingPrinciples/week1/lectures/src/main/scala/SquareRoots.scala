object SquareRoots {
  def solution(n: Double): Unit = {

    def abs(x: Double) = if (x < 0) -x else x

    @scala.annotation.tailrec
    def sqrtIter(guess: Double, x: Double): Double = {
      if (isGoodEnough(guess, x)) guess
      else sqrtIter(improve(guess, x), x)
    }

    def isGoodEnough(x: Double, y: Double): Boolean = abs(x * x - y) / x < 0.001

    def improve(guess: Double, x: Double): Double = (guess + x / guess) / 2

    def sqrt(x: Double): Double = sqrtIter(1.0, x)

    println(sqrt(n))
  }
}
