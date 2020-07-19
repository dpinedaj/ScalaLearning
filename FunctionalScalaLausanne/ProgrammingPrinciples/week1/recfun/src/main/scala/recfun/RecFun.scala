package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    @scala.annotation.tailrec
    def balIter(chars: List[Char], count: Int): Boolean = {
      if (chars.isEmpty) {
        if (count == 0) true else false
      } else {
        if (chars.head.toString == ")") {
          if (count < 1) false else balIter(chars.tail, count - 1)
        } else if (chars.head.toString == "(") {
          balIter(chars.tail, count + 1)
        } else {
          balIter(chars.tail, count)
        }
      }
    }
    balIter(chars, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else {
      val h = coins.head
      countChange(money - h, coins) + countChange(money, coins.tail)
    }
  }
}
