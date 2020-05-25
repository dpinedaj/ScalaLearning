package examples
object IfStatement {
  /* With logical operators in scala
    we follow the "if" -> "else if" -> "else"
    line of logic*/

  var animals = List("aardvark", "hedgehog", "walrus")

  //if statement #1
  if ((animals.contains("aardvark") && animals.contains("walrus"))) {
    println("#1: Aardvarkwalrus!")
  }

  //if - elseif
  //if statement #2
  if ((animals.contains("aardvark") && animals.contains("rinhos"))) {
    println("#2: Aardvarkocerous!")
  } else if ((animals.contains("aardvark") || animals.contains("rinhos"))) {
    println("#2: Aardvark or Rhino?")
  }

  //if -elseif -else
  //if statement #3
  if ((animals.contains("penguin") && animals.contains("rihno"))) {
    println("#3: penhuarhino!")
  } else if ((animals.contains("penguin") || animals.contains("rhino"))) {
    println("#3: penguin or rinho?")
  } else {
    println("#3: Welcome to Marmot land!")
  }

  //Annided if statement
  val k = 10
  println(if (k == 10) "K value is 10" else "K value is not 10")

  println("\n")
  for (i <- 0 until 10) {
    print(if (i % 2 == 0) s"$i is even " else s"$i is odd ")
  }

}
