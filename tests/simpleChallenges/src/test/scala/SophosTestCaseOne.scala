import sophosTest.StudentScore


object SophosTestCaseOne {
  /*A lo largo de un curso se realizan dos exámenes parciales.
Para aprobar el curso la nota media debe ser mayor o igual que
 5 siempre y cuando en ambos parciales se tenga al menos un 4.
 Escribir un programa que pregunte al usuario la nota de los
 dos parciales y muestre por pantalla si el alumno ha aprobado
 el curso o si no, y en caso de no haber aprobado,
qué parcial tiene que repetir por tener menos de 4 en él. (CÓDIGO)
   */

  def main(args: Array[String]): Unit = {
    val n:Int = StudentScore.getInt

    /*var scores= List[Double]()
    for (i <- 1 to n) {
      scores ::= StudentScore.getScore(i)
    }*/
    val scores = (for(i <- 1 to n) yield StudentScore.getScore(i)).toList
    val pass: String = StudentScore.checkPass(scores)
    println(pass)
  }

}
