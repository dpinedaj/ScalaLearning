package examples

object aritmethicOperationsTest {
  def main(args: Array[String]) = {
    //sum
    val a = 2 + 7
    println(a)

    //substraction
    val b = 4 - 2
    println(b)

    //multiplication
    val c = 4 * 5
    println(c)

    //division
    val d = 9 / 4
    println(d)

    //modulus
    val e = 7 % 2
    println(e)

    //pow
    val f: Double = math.pow(3, 3) //math.pow returns by default a double value
    println(f)

    //square root
    val g = math.sqrt(64)
    println(g)

    //absolute
    val h = math.abs(-7.8)
    println(h)
  }
}
