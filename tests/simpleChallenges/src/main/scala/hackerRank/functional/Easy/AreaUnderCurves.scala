package hackerRank.functional.Easy


object AreaUnderCurves {

  def f(coefficients: List[Int], powers: List[Int], x: Double): Double = {

    def iter(coefs: List[Int], pows: List[Int], acc: Double): Double = {
      if (coefs.isEmpty | pows.isEmpty) acc
      else acc + iter(coefs.tail, pows.tail, coefs.head * math.pow(x, pows.head))
    }

    iter(coefficients, powers, 0)


  }

  def area(coefficients: List[Int], powers: List[Int], x: Double): Double = {
    math.Pi * math.pow(f(coefficients, powers, x), 2)
  }


  def summation(func: (List[Int], List[Int], Double) => Double,
                upperLimit: Int,
                lowerLimit: Int,
                coefficients: List[Int],
                powers: List[Int]): Double = {
    val step = 0.001
    val subIntervals = BigDecimal(lowerLimit) to BigDecimal(upperLimit) by step
    subIntervals.foldLeft(0.0) { case (acc, x) => acc + func(coefficients, powers, x.toDouble) * step }
  }


}