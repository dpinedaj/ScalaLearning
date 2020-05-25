package examples
object Functions {
  class functions {
    //sum function
    def sum(a: Int, b: Int) = a + b

    /*Is possible to assign the output as : Int
    If the function is public is recommended to use it*/
    def sumDefined(a: Int, b: Int): Int = a + b

    def divide(a: Int, b: Int): Int = {
      /* here can be the documentation
        and can be in many lines */
      val dividend = a
      val divisor = b
      dividend / divisor
    }

    def anothersum(a: Int, b: Int): Int = {
      return a + b

    }
    def isPrime(num: Int): Boolean = {
      for (n <- 2 until num) {
        if (num % n == 0) {
          return false
        }
      }
      return true
    }
  }

  val func = new functions
  val a = func.sum(10, 20)
  println(a)

  val b = func.divide(20, 10)
  println(b)
  println(func.isPrime(3))
}
