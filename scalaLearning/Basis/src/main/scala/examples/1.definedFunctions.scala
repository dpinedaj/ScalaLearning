package examples
object definedFunctionsTest {
  def main(args: Array[String]) = {
    System.out.println("Hello World") //This is the full call of println

    println("Hello World")

    val a = "world"
    val b = 123
    val c = 1.5
    printf("Hello %s %d  %.2f", a, b, c)
  }
}
