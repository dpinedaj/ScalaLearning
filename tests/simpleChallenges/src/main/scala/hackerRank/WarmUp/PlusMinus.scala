package hackerRank.WarmUp

object PlusMinus {

  def plusMinus(arr: Array[Int]): Unit = {
    val len = arr.length.toDouble
    val pos = arr.count(_>0) / len
    val neg = arr.count(_ < 0) / len
    val zero = arr.count(_ == 0) / len

    println(f"$pos%1.5f\n$neg%1.5f\n$zero%1.5f")

  }

  def solution(): Unit = {

    val arr = io.StdIn.readLine.split(" ").map(_.trim.toInt)
    plusMinus(arr)
  }

}
