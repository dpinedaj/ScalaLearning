package examples.data_flow

object ForLoops {

  //walking through a list
  val badgers = List("mushrooms", "mushrooms", "it's a snake")
  //for loop can be just in the line
  for (item <- badgers) println(item)

  //looping through a range
  for (i <- Array.range(0, badgers.size)) {
    println(s"$i -> ${badgers(i)}")
  }
  //looping through a range explicit
  for (i <- 0 until badgers.size) {
    println(s"$i -> ${badgers(i)}")
  }
  //Looping with if statement
  println("\nfoor loop for even numbers")
  for (i <- Range(0, 10)) {
    //Note: can be called as Range or Array.range
    if (i % 2 == 0) {
      println(s"$i is even")
    } else {
      println(s"$i is odd")
    }
  }

  //!Foreach
  val languages: Seq[String] = Seq("Java", "Scala", "Python", "Swift", "Golang")
  languages.foreach(l => println(s"$l is a pretty nice language")) //For each don't transform the collection, just iterate it and use his elements

  //!Double for loop in a line
  val range1 = Range(1, 10)
  val range2 = Array.range(20, 40, 2)
  println("Double for loop")
  for (i <- range1; j <- range2) {
    println(s"$i ---> $j")
  }

  //!Conditional into a for loop
  println("Conditional for loop")
  for (i <- range1 if (i % 2 == 0); j <- range2 if (j % 5 == 0)) {
    println(s"$i ---> $j")
  }

  //!for loop yielding results
  val myNums = for {i <- 1 to 3 ; j <- 1 to i} yield i * j //res0: IndexedSeq[Int] = Vector(1, 2, 4, 3, 6, 9)

  //filtering inside
  val myNums2 = for {i <- 1 to 3 if i % 2 == 0;
                     j <- 1 to i} yield i * j//res0: IndexedSeq[Int] = Vector(2, 4)

}
