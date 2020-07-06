package sophosTest

object TextJobs {
  def nGrams(text: String, n: Int): List[String] = {
    (for (i <- 0 to (text.length - n)) yield text.substring(i, i + n)).toList
  }
}
