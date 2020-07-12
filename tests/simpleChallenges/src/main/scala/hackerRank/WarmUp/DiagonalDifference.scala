package hackerRank.WarmUp

object DiagonalDifference {

  def diagonalDifference(ar: Array[Array[Int]]): Int = {
    val len = ar.length
    val mainDiagonal = (for (i <- 0 until len) yield ar(i)(i)).sum
    val reverseDiagonal = (for ((i, j) <- (len - 1 to 0 by -1) zip (0 until len)) yield ar(i)(j)).sum
    math.abs(mainDiagonal - reverseDiagonal)
  }

  def solution(): Unit = {
    println("Insert the dimension of the matrix")
    val n = io.StdIn.readLine.trim.toInt

    val arr = Array.ofDim[Int](n, n)

    for (i <- 0 until n) {
      println(s"Insert the ${i + 1} row of the matrix")
      arr(i) = io.StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    }

    val result = diagonalDifference(arr)

    println(s"The diagonal difference is $result")

  }
}
