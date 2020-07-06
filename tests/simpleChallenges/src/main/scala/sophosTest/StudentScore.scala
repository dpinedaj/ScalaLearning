package sophosTest

object StudentScore {

  def getInt: Int = {
    while (true) {
      val score: String = io.StdIn.readLine(s"Insert num of scores: ")
      try {
        val value = score.toInt
        return value
      } catch {
        case _: Throwable => println(s"$score can't be treated as a Double value")
      }
    }
    throw new IllegalStateException("This should never happen")
  }


  def getScore(n: Int): Double = {
    while (true) {
      val score: String = io.StdIn.readLine(s"Insert score $n: ")
      try {
        val value = score.toDouble
        return value
      } catch {
        case _: Throwable => println(s"$score can't be treated as a Double value")
      }
    }
    throw new IllegalStateException("This should never happen")
  }
  def checkPass(scores: List[Double]): String ={

    val meanScore: Double = scores.sum / scores.length
    val passScores: Int = scores.map{
      x => if(x>=4.0) 1 else 0
    }.sum

    if (meanScore >= 5 && passScores == scores.length) "The student passed"
    else "The student doesn't passed"
  }
}