package examples

object DefinedFunctions {

  System.out.println("Hello World") //This is the full call of println

  println("Hello World")

  val a = "world"
  val b = 123
  val c = 1.5
  printf("Hello %s %d  %.2f", a, b, c)
}

object SynteticMethods {
  case class Time(hours: Int = 0, minutes: Int = 0)

  val time = Time(9, 0)
  //equals()
  time == Time(9) //res0: Boolean =true
  time == Time(9, 30) //res0: Boolean = false

  //hashCode
  Time(9, 45).hashCode() // res0: Int = 471971180

  //toString
  Time() //res0: Time = Time(0, 0) --> by default the toString() method returns the instance with values

  //copy
  var time2 = Time(9, 45)
  time2 = time2.copy(minutes = 50) //res0: Time = Time(9, 50)
}
