object stringOperators {

  def main(args: Array[String]) = {
    //Coparison operators
    val a = 10
    println(a == 10)
    println(a != a + 5)
    println(a >= a - 5)
    println(15 >= a)

    //string concatenation
    val string1 = "hello"
    val string2 = "world"
    println(string1 * 3)
    println(string1 + " " + string2)

    //strings methods
    //lenght
    val string3 = "Hello world"
    val len = string3.length
    println(len)

    //last
    var las = string3.last
    println(las)

    //S-interpolation
    val animal = "llamas"
    var hosieryAd = "Now, you can buy hosiery" +
      s" specifically designed for ${animal}"
    println(hosieryAd)

    //F-interpolation
    hosieryAd = "Now, you can buy hosiery" +
      f" specifically designed for $animal"
    println(hosieryAd)
    printf("check for %s", animal)
    println("\n")

    //indexing
    val pos = animal.charAt(0)
    println(pos)
    val search = animal.indexOf("l")
    println(search)
    println(
      s"${animal.charAt(0)} is in " +
        s"${animal.indexOf(animal.charAt(0))}"
    )

    //Slice a string
    val slc = hosieryAd slice (7, 12)
    println(slc)
    val mch =
      hosieryAd matches "llamas" //if all the variable is equal to the comparison
    println(mch)
    val cnts =
      hosieryAd contains "llamas" //if the comparison is just a part or full the variable
    println(cnts)

  }
}
