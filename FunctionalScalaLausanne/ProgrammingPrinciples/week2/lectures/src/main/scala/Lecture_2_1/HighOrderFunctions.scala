package Lecture_2_1

object HighOrderFunctions {
  def usage():Array[Int] = {
    Array[Int=>Int](id, cube, fact).map(x => sum(x, 1, 5))
  }
  //general example
  //It can receive dinamically a function and use it in many ways
  //That function receive a function to use it further, the parameter must be an "arrow function" with the in and out type
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  }


  //Individual examples
  def id(x: Int): Int = x

  def sumInt(a: Int, b: Int): Int = {
    if (a > b) 0 else id(a) + sumInt(a + 1, b)
  }

  def cube(x: Int): Int = x * x * x

  def sumCubes(a: Int, b: Int): Int = {
    if (a > b) 0 else cube(a) + sumCubes(a + 1, b)
  }


  @scala.annotation.tailrec
  def fact(x: Int): Int = if (x == 0) 1 else fact(x - 1)

  def sumFactorials(a: Int, b: Int): Int = {
    if (a > b) 0 else fact(a) + sumFactorials(a + 1, b)
  }

}
