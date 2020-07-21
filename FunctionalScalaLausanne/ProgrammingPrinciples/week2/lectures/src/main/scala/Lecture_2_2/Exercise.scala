package Lecture_2_2


object Exercise {

  def usage(): Int = {
    newProduct(x => x * x)(3, 4)
  }

  //Initial functions
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  }

  def fact(x: Int): Int = product(x => x)(1, x)


  //Generalized function "MapReduce" can be used like the before defined product or any defined function
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  def newProduct(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)

}
