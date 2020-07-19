package hackerRank.Easy

object AppendDelete {
  def appendAndDelete(s: String, t: String, k: Int): String = {
    val totalLength = s.length + t.length
    if (k >= totalLength) return "Yes"

    val min = Math.min(s.length, t.length)
    val sameLength = (0 until min)
      .find(i => s(i) != t(i))
      .getOrElse(min)

    val diff = totalLength - 2 * sameLength
    if (k >= diff && (k - diff) % 2 == 0) "Yes"
    else "No"

  }

  def solution() {
    val s = io.StdIn.readLine
    val t = io.StdIn.readLine
    val k = io.StdIn.readLine.trim.toInt
    val result = appendAndDelete(s, t, k)
    println(result)
  }
}
