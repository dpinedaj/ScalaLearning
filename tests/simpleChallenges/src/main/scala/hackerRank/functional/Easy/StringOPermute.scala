package hackerRank.functional.Easy
// Reference: https://www.hackerrank.com/challenges/string-o-permute/problem
object StringOPermute{

  def Run(n: Int): Unit ={
    for (i <- 1 to n){
      val incoming_word = io.StdIn.readLine()
      val w = incoming_word.toArray
      println(w.zipWithIndex.map(
        value =>
          if (value._2 % 2 == 0) w(value._2 + 1)
          else w(value._2 - 1)
      ).mkString(""))
    }
  }
}

