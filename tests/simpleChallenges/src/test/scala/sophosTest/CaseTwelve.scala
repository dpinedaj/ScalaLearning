package sophosTest

object CaseTwelve {
  def main(args: Array[String]): Unit = {
    val a: String = "1231"
    val b: String = "hello"
    val c: String = "123ka2123"

    for (i <- Array(a, b, c)) {
      println(i, "--->", NumberCast.isNumber(i))
    }
  }
}
