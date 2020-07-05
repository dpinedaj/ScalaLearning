package sophosTest

object NumberCast {
  /*
  ¿Cómo validamos que una cadena/string es completamente numérica?
   */

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
