package hackerRank.problemSolving.Easy

object SequenceEquation {
  def permutationEquation(n: Int, p: Array[Int]): Array[Int] = {
    val range = 1 to n
    val iter = 0 until n
    val result = (for (k <- for (j <- range; i <- iter if p(i) == j)
      yield i + 1; i <- iter if p(i) == k)
      yield i + 1).toArray

    result
  }

  def solution() {
    val stdin = scala.io.StdIn
    val n = stdin.readLine.trim.toInt
    val p = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = permutationEquation(n, p)
    println(result.mkString("\n"))
  }
}
