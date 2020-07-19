def squares(a: Int, b: Int): Int = {
  def sqIter(n: Int): Int = {
    if (math.sqrt(n).toString.length > 6) 0
    else 1
  }
  val result = (for(i<-a to b) yield sqIter(i)).sum
  result
}


squares(3, 9)























































































