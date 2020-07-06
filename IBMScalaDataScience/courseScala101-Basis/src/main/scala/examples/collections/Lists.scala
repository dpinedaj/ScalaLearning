package examples.collections


object Lists {

  /*In scala lists are immutable
        elements, is a mutable version
        called ListBuffer
   */
  //This is a String type list
  val animals = List("aardvark", "penguin", "hippo", "sloth")

  //This is an integer type list
  var numbers = List(8, 9, 18, 1, 57, 2, 2, 1, 57)
  //To append a new number into List
  numbers = numbers :+ 15 //It will append 15 to the end of the list
  numbers = 0 +: numbers //It will append 0 to the beginning of the list
  
  numbers = numbers.distinct //It will drop the repeated elements of the list
  //This is an "any" type list
  val stuff1 = List("aardvark", 65, 3.14)
  println(
    s"number 1: ${numbers(0)}\n" +
      s"number 2: ${numbers(1)}\n" +
      s"number 3: ${numbers(2)}"
  )
  //This returns the first element of the list
  val hd = stuff1.head //res0: Any = aardvark
  
  //The last element
  val tl = stuff1.tail //res0: List[Any] = List(65, 3.14)
  
  //The first n elements
  var n = 2
  val tk = stuff1.take(n) //res0: List[Any] = List(aardvark, 65)

  //to check if the list contains some element
  val ctns = stuff1.contains(65) //res0: Boolean = true


  //To sort the list
  val newNumbers = numbers.sorted //res0: List[Int] = List(1, 2, 8, 9, 18, 57)


  //The list size or lenght
  val s = newNumbers.size //res0: Int = 6
  //The minimum value of the list
  val m = newNumbers.min //res0: Int = 1
  //The maximum value of the list
  val ma = newNumbers.max //res0: Int = 57
  //The sum of the elements
  val su = newNumbers.sum //res0: Int = 95
  //The product of the elements
  val pro = newNumbers.product
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
  val combo = List(List(2, 7, 9), List(62, 33, 4)) //res0: com
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

  var listInLine = (for(i<-1 to 10 if i%2==0) yield i).toList
  

}
