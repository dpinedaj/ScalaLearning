package examples.extra

object StringOperators {

  //Coparison operators
  val a = 10
  a == 10 //res0: Boolewn = true
  a != a + 5 //res0: Boolewn = true
  a >= a - 5 //res0: Boolewn = true
  15 >= a //res0: Boolewn = true

  //string concatenation
  val string1 = "hello"
  val string2 = "world"
  string1 * 3 //res0: String = hellohellohello
  string1 + " " + string2 //res0: String = hello world

  //strings methods
  //lenght
  val string3 = "Hello world"
  val len = string3.length //res0: Int = 11

  //last
  var las = string3.last //res0: Char = d

  //S-interpolation
  val animal = "llamas"
  var hosieryAd = "Now, you can buy hosiery" +
    s" specifically designed for ${animal}"

  //F-interpolation
  hosieryAd = "Now, you can buy hosiery" +
    f" specifically designed for $animal"
  printf("check for %s", animal)

  //format interpolation
  println("check for %s".format(animal))
  //indexing
  val pos = animal.charAt(0) //res0: Char = l

  val search = animal.indexOf("l") //res0: Int = 0

  s"${animal.charAt(0)} is in " +
    s"${animal.indexOf(animal.charAt(0))}"

  //Slice a string
  val slc = hosieryAd slice (7, 12) //res0: String = u can

  val mch =
    hosieryAd matches "llamas" //if all the variable is equal to the comparison
  //res0: Boolean = false
  val cnts =
    hosieryAd contains "llamas" //if the comparison is just a part or full the variable
  //res0: Boolean = true

}
