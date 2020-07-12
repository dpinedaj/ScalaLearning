package sophosTest

object TestCases {

  def main(args: Array[String]): Unit = {
    caseOne()
    caseTwo()
    caseThree()
    caseTwelve()
  }
  def caseOne(): Unit = {
    /*A lo largo de un curso se realizan dos exámenes parciales.
    Para aprobar el curso la nota media debe ser mayor o igual que
    5 siempre y cuando en ambos parciales se tenga al menos un 4.
    Escribir un programa que pregunte al usuario la nota de los
    dos parciales y muestre por pantalla si el alumno ha aprobado
    el curso o si no, y en caso de no haber aprobado,
    qué parcial tiene que repetir por tener menos de 4 en él. (CÓDIGO)
     */
    val n: Int = StudentScore.getInt

    /*var scores= List[Double]()
    for (i <- 1 to n) {
      scores ::= StudentScore.getScore(i)
    }*/
    val scores = (for (i <- 1 to n) yield StudentScore.getScore(i)).toList
    val pass: String = StudentScore.checkPass(scores)
    println(pass)
  }

  def caseTwo(): Unit = {
    /*
    Un n-grama es una secuencia de 𝑛 caracteres consecutivos de una cadena.
     Por ejemplo, los 3-gramas de la cadena 'Python' son 'Pyt', 'yth', 'tho' y 'hon'.
     Escribir un programa que pregunte por una cadena y un número entero positivo 𝑛
     y muestre por pantalla todos los n-gramas de la cadena.(CÓDIGO)
     */
    val l: List[String] = TextJobs.nGrams("Python", 3)
    println(l.mkString(", "))
  }



  def caseThree(): Unit = {
    /*
    Escribir un programa que elimine de una lista dada todos
    los elementos repetidos y muestre por pantalla los elementos
    de la lista sin repeticiones.
     */
    val myList = List(1,2,3,4,5,1,1,2,1,1,1,2,2,3,3,4,1,3,2)
    println(myList.toSet.toList.sorted.mkString(", "))
  }



  def caseTwelve(): Unit = {
    /*
    ¿Cómo validamos que una cadena/string es completamente numérica?
    */
    val a: String = "1231"
    val b: String = "hello"
    val c: String = "123ka2123"

    for (i <- Array(a, b, c)) {
      println(i, "--->", NumberCaster.isNumber(i))
    }
  }
}
