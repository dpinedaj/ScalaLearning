package examples
object flattenTests {

  def main(args: Array[String]): Unit = {
    val numbers =
      List(List(1, 2, 6), List(2, 3, 5), List(5, 7, 1), List(4, 8, 3))
    println(s"The initial numbers list is: $numbers")
    //The flatten method allows me to extract all the elements of every single list
    //into the main list, and mix them together
    val numbersFlat = numbers.flatten
    println(s"The flatten numbers list is: $numbersFlat")

    //flatmap allows to flat the collection applying any function in the way
    val numbersFatMap = numbers.flatMap { x => x.map { n => n * 2 } }
    println(s"The FlattenMap numbers list is $numbersFatMap")

    val basicNumbers = List(4, 8, 15, 16, 23, 42)
    println(s"The basic combination are : $basicNumbers")
    val basicToTwo = basicNumbers.flatMap { x => List(x, x * 2) }
    println(s"The converted combination are: $basicToTwo")

  }
}
