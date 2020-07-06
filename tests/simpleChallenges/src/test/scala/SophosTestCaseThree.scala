object SophosTestCaseThree {
  /*
  Escribir un programa que elimine de una lista dada todos
los elementos repetidos y muestre por pantalla los elementos
de la lista sin repeticiones.
   */

  def main(args: Array[String]): Unit = {
  val myList = List(1,2,3,4,5,1,1,2,1,1,1,2,2,3,3,4,1,3,2)
    println(myList.toSet.toList.sorted.mkString(", "))
  }

}
