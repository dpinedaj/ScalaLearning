package Lecture_2_3

import math.abs

object FindFixedPoints extends App {
  /* A number x is called a fixed point of a function f if  f(x) = x
  *  an example is to find sqrt(x) -->    y => x/y but using fixedPoint for this, it doesn't converge
  *  it will oscilate between 1 and 2, so never converge
  *
  * To fix it, you can do y=> (y+x/y)/2
  *
  * Resuming the above, you can stabilize a value using an general averaging like:
  *   (x + f(x) ) / 2 ---> it will converge averaging every single value and computing it
  * */
  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double): Boolean = {
    abs((x - y) / x) / x < tolerance
  }

  def fixedPoint(f: Double => Double)(firstGuess: Double): Double = {
    @scala.annotation.tailrec
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }

    iterate(firstGuess)
  }

  println(fixedPoint(x => 1 + x / 2)(1))

  //def sqrt(x: Double): Double = fixedPoint(y => x / y)(1.0)
  def sqrt(x: Double): Double = fixedPoint(y => (y + x / y) / 2)(1.0)

  def averageDamp(f: Double => Double)(x: Double): Double = (x + f(x)) / 2

  def fixedSqrt(x: Double): Double = fixedPoint(averageDamp(y => x/y))(1)

}
