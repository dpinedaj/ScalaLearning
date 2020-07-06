package sophosTest

object NumberCaster {

  def isNumber(text: String): Boolean = {
    try {
      text.toInt
      val number: Boolean = true
      number
    } catch {
      case _: Throwable => false
    }
  }
}
