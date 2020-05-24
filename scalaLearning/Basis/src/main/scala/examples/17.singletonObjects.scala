package examples

object HelloObject {
    def echo(msg: String): Unit = {
        println(msg)
    }
}
object singletonTest {
  //This is a basic Singleton Object
  //The main method is needed to run it, the debuger will always search
  //for a main method to run it.
  def main(args: Array[String]): Unit = {
    val n: Int = 100
    println("This is %d using a format".format(n))
  }

  //The apply method is special and can be called using:
  // singlethonTest(val1, val2)
  def apply(val1:String, val2:Int): String ={
    return s"The string val is: $val1, the int val is: $val2"
  }
}
