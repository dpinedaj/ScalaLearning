package hackerRank.problemSolving.WarmUp

object SolveMeFirst {
  //Sum 2 integers
  def solution():Unit = {
    //It let read 2 first integers and sum (need exceptions control)
    println(io.Source.stdin.getLines().take(2).map(_.toInt).sum)
  }
}
