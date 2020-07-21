package Lecture_2_2

import Lecture_2_1.HighOrderFunctions.fact

object Currying {

  def usage(): Int = {
    sumCubes(1, 10) + otherSum(fact)(10, 20) + sum(x => x)(1, 10)
  }

  //Functions that returns functions
  //That function return a new function using the function passed to proceed
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int = {
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    }

    sumF
  }

  //With this we can then define:
  def sumCubes: (Int, Int) => Int = sum(x => x * x * x)

  //Another sum including itself the function
  def otherSum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }
}
