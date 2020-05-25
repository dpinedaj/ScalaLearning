package examples

object HelloObject {
  def echo(msg: String): Unit = {
    println(msg)
  }
}
object SingletonExample {
  //This is a basic Singleton Object
  //The main method is needed to run it, the debuger will always search
  //for a main method to run it.

    val n: Int = 100
    println("This is %d using a format".format(n))


  //The apply method is special and can be called using:
  // singlethonTest(val1, val2)
  def apply(val1: String, val2: Int): String = {
    return s"The string val is: $val1, the int val is: $val2"
  }
}


/*Whit the apply method, it can be used directly on calling the object
as there:
>>>Reverse("Hello World")
*/
object Reverse {
  def apply(s: String): String =
    s.reverse
}

object reverseTest {
  def main(args: Array[String]): Unit = {
    val a = "Hello World"
    val aReversed = Reverse(a)
    println(aReversed)
  }
}
