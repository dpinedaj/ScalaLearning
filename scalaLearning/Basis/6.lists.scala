object lists {
  def main(args: Array[String]) = {
    /*In scala lists are immutable
        elements, is a mutable version
        called ListBuffer
     */
    //This is a String type list
    val animals = List("aardvark", "penguin", "hippo", "sloth")
    println(animals)

    //This is an integer type list
    val numbers = List(8, 9, 18, 1, 57, 2)
    println(numbers)

    //This is an "any" type list
    val stuff1 = List("aardvark", 65, 3.14)
    println(stuff1)
    println(
      s"number 1: ${numbers(0)}\n" +
        s"number 2: ${numbers(1)}\n" +
        s"number 3: ${numbers(2)}"
    )
    //This returns the first element of the list
    val hd = stuff1.head
    println(s"The fist element is: ${hd}")
    //The last element
    val tl = stuff1.tail
    println(s"The last element is: ${tl}")
    //The first n elements
    var n = 2
    val tk = stuff1.take(n)
    println(s"The fist ${n} elements are: ${tk}")

    //to check if the list contains some element
    val ctns = stuff1.contains(65)
    println(s"This list contains 65?: ${ctns}")

    //To sort the list
    val newNumbers = numbers.sorted
    println(newNumbers)

    //The list size or lenght
    println(s"The list size is ${newNumbers.size}")
    //The minimum value of the list
    println(s"The minimum value is ${newNumbers.min}")
    //The maximum value of the list
    println(s"The maximum value is ${newNumbers.max}")
    //The sum of the elements
    println(s"The sum of the elements is ${newNumbers.sum}")
    //The product of the elements
    println(s"The product of the elements is ${newNumbers.product}")
    //To drop n first elements in the list
    n = 2
    val newNumbers2 = newNumbers.drop(n)
    println(
      s"The list without the first ${n}" +
        s" elements is ${newNumbers2}"
    )

    //To take the right n elements as with take
    println(s"The last ${n} elements are:")
    for (e <- newNumbers.takeRight(n)) {
      println(e)
    }

    //Embedded list
    val combo = List(List(2, 7, 9), List(62, 33, 4))
    println(combo)
    for (i <- combo) {
      for (j <- i) {
        println(j)
      }
    }

    //slicing
    val slc = newNumbers slice (1, 3) //It will take elements with index 1 and 2
    println(slc)

    //list of tuples
    val lstTuples = List(("aardvark", 1), ("hippo", 2), ("hedgehog", 3))
    println(lstTuples)
    
    //adding elements to a list
    //It adds the element to the first position of the list
    var animals2 = List("aardvark", "hedgehog", "walrus")
    animals2 = "hippo" :: animals2
    println(animals2)

    val animals3 = "zebra" :: animals2
    println(animals3)
    

    

  }
}
