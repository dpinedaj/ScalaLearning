object Factorial {
  def factorial(n: Int): Unit = {

    @scala.annotation.tailrec
    def loop(acc: Int, n: Int): Int = {
      if (n == 0) acc
      else loop(acc * n, n - 1)
    }

    println(loop(1, n))
  }
}
