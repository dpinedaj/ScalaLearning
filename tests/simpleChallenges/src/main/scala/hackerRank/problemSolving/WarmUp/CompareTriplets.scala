package hackerRank.problemSolving.WarmUp

object CompareTriplets {

  /*
    Alice and Bob each created one problem for HackerRank. A reviewer rates the two challenges, awarding points on a scale from 1 to 100 for three categories: problem clarity, originality, and difficulty.

    The rating for Alice's challenge is the triplet a = (a[0], a[1], a[2]), and the rating for Bob's challenge is the triplet b = (b[0], b[1], b[2]).

    The task is to find their comparison points by comparing a[0] with b[0], a[1] with b[1], and a[2] with b[2].

        If a[i] > b[i], then Alice is awarded 1 point.
        If a[i] , then Bob is awarded 1 point.
        If a[i] = b[i], then neither person receives a point.

    Comparison points is the total points a person earned.

    Given a and b, determine their respective comparison points.

    Example

    a = [1, 2, 3]
    b = [3, 2, 1]

        For elements *0*, Bob is awarded a point because a[0] .
        For the equal elements a[1] and b[1], no points are earned.
        Finally, for elements 2, a[2] > b[2] so Alice receives a point.

    The return array is [1, 1] with Alice's score first and Bob's second.
   */
  def compareTriplets(a: Array[Int], b: Array[Int]): Array[Int] = {
    (a zip b).map{
      x => if (x._1 > x._2) Array(1,0)
      else if (x._1 == x._2) Array(0,0)
      else Array(0,1)
    }.reduce{
      (x,y) => Array(x(0) + y(0), x(1) + y(1))
    }
  }

  def solution(): Unit = {

    val a = io.StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val b = io.StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val result = compareTriplets(a, b)

    println(result.mkString(" "))
  }
}