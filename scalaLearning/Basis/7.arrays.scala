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


     // Array is an IndexedSeq
    val fruits = Array[String]("apple", "orange", "bannana")

    var n: Int = 1
    //To search index ----> method apply
    println(s"The $n element is ${fruits.apply(n)}, called with apply")
    //apply is by default as ()
    println(s"The $n element is ${fruits(n)}, called without apply")
    //To check lenght
    println(s"The lenght is: ${fruits.length}")
    //To check if is empty
    println(s"Fruits is empty? ${fruits.isEmpty}")
    println(s"Fruits have any element? ${fruits.nonEmpty}")

    //To check an object index
    var obj: String ="apple"
    println(s"The index of $obj is ${fruits.indexOf(obj)}")
  
    //! Map method
  }

}
