package hackerRank.problemSolving.Easy

object CutSticks {
  def cutTheSticks(arr: Array[Int]): Array[Int] = {

    @scala.annotation.tailrec
    def loop(arr: Array[Int], res: Array[Int]): Array[Int] = {
      val len = arr.length
      if (len > 0) {
        val res2 = res :+ len
        val min = arr.min
        val arrF = arr.filter(x => x > min)
        loop(arrF, res2)
      } else res
    }

    loop(arr, Array())

  }

  def solution() {
    val stdin = scala.io.StdIn
    val n = stdin.readLine.trim.toInt
    val arr = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = cutTheSticks(arr)
    println(result.mkString("\n"))
  }
}
