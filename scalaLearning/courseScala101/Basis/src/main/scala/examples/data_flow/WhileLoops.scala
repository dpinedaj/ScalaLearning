package examples.data_flow

object WhileLoops {
  import util.control.Breaks._
  def whilebreak() = {
    //Using break statement, in this case break with also close the main function
    println("\nwhile loop with break")
    var y = 0
    breakable {
      while (true) {
        println(s"y is currently $y; adding 1 to y")
        y += 1
        if (y == 5) break
      }
    }
  }

  //using condition in loop
  println("\nwhile loop for x")
  var x = 0
  while (x < 5) {
    println(s"x is currently $x; adding 1 to x")
    x += 1
  }

  //do - while loops
  println("\ndo-while loop")
  var k = 0
  do {
    println(s"k vale $k")
    k += 1
  } while (k < 5)

  whilebreak()

}
