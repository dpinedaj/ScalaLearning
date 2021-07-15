package hackerRank.functional.Easy

import scala.annotation.tailrec

object FibonacciModified extends App{
  def fibonacci(t1: BigInt, t2: BigInt, n: BigInt): BigInt = {
    @tailrec
    def iterFibonacci(prev1: BigInt, prev2: BigInt, step: BigInt): BigInt = {
      if (step > n) prev2
      else {
        iterFibonacci(prev2, prev1 + prev2.pow(2), step + 1)
      }
    }
    iterFibonacci(t1, t2, 3)
  }

  println(fibonacci(0, 1, 10))
}
