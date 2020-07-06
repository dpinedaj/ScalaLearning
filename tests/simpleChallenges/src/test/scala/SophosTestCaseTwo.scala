import sophosTest.TextJobs

object SophosTestCaseTwo {
  /*
  Un n-grama es una secuencia de 𝑛 caracteres consecutivos de una cadena.
   Por ejemplo, los 3-gramas de la cadena 'Python' son 'Pyt', 'yth', 'tho' y 'hon'.
   Escribir un programa que pregunte por una cadena y un número entero positivo 𝑛
   y muestre por pantalla todos los n-gramas de la cadena.(CÓDIGO)
   */

  def main(args: Array[String]): Unit = {
    val l: List[String] = TextJobs.nGrams("Python", 3)
    println(l.mkString(", "))
  }


}
