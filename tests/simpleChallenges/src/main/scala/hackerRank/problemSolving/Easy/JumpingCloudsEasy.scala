package hackerRank.problemSolving.Easy

object JumpingCloudsEasy{
  def jumpingOnClouds(c: Array[Int]): Int = {

    @scala.annotation.tailrec
    def helper(i: Int, jumps: Int): Int = {
      if (i == c.length - 1) jumps
      else if (i + 2 <= c.length - 1 && c(i + 2) == 0) helper(i + 2, jumps + 1)
      else helper(i + 1, jumps + 1)
    }

    helper(0, 0)
  }

}
