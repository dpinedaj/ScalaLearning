package Lecture_2_1

import HighOrderFunctions.sum

object AnonymousFunctions {
  //Anonymous or arrow functions definition
  (x: Int) => x * x * x
  (x: Int, y: Int) => x + y

  //Example using the previous function
  def sumInt(a: Int, b: Int): Int = sum(x => x, a, b)

  def sumCubes(a: Int, b: Int): Int = sum(x => x * x * x, a, b)

  def usage(): Int = {
    def sum(f: Int => Int)(a: Int, b: Int): Int = {
      @scala.annotation.tailrec
      def loop(a: Int, acc: Int): Int = {
        if (a > b) acc
        else loop(a + 1, f(a) + acc)
      }
      loop(a, 0)
    }
    sum(x => x*x)(3, 5)
  }


}
