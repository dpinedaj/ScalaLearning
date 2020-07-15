package hackerRank.WarmUp

object TimeConversion {
  def timeConversion(s: String): String = {
    val pattern: String = "\\D"
    val ar = s.split(pattern)
    if (s.endsWith("PM") && ar(0) != "12") {
      ar(0) = (ar(0).toInt + 12).toString
    } else if (s.endsWith("AM") && ar(0) == "12") {
      ar(0) = "00"
    }
    ar.mkString(":")

  }

  def solution() {
    val s = io.StdIn.readLine
    val result = timeConversion(s)
    println(result)

  }
}
