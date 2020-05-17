object arrays {
  /*In scala, list aren't mutable, but
    arrays do*/
  def main(args: Array[String]) = {
    //An array of integers
    val arr1 = Array(3, 6, 9)
    for (i <- 0 until arr1.length) {
      println(arr1(i))
    }

    //anytype array
    val arr2 = Array(3.14, 62, "walrus")
    for (i <- 0 until arr2.length) {
      println(arr2(i))
    }

    //!Ranges arrays
    val arrang = Array.range(0, 30, 3)
    for (i <- 0 until arrang.length) {
      println(arrang(i))
    }

  }

}
