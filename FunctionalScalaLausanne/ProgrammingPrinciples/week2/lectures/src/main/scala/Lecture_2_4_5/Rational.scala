package Lecture_2_4_5

class Rational(x: Int, y: Int) {
  require(y != 0, "The denominator can't be zero")

  @scala.annotation.tailrec
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def this(x: Int) = this(x, 1)

  def numer: Int = x

  def denom: Int = y

  def < (that: Rational): Boolean = {
    numer * that.denom < that.numer * denom
  }

  def >(that: Rational): Boolean = {
    numer * that.denom > that.numer * denom
  }
  def max(that: Rational): Rational = {
    if (this < that) that else this
  }

  def + (that: Rational): Rational = {
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  }

  def neg: Rational = {
    new Rational(-numer, denom)
  }

  def - (that: Rational): Rational = {
    this + that.neg
  }

  def * (that: Rational): Rational = {
    new Rational(
      numer * that.numer, denom * that.denom
    )
  }

  override def toString: String = {
    val g = gcd(numer, denom)
    numer / g + "/" + denom / g
  }
}

object Rationals {
  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  x - y - z
  y + y
  x * y
  x < y
  x max y
  x > y
  val strange = new Rational(1)
  strange + strange
}