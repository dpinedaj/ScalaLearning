package bullRaider

object flowControl {

  def calculator(): Unit = {
    for (i <- 1 to 10) {
      for (j <- 1 to 10) {
        print((i * j).toString + "\t")
      }
      println()
    }
  }

  def pairOfTuples(): Unit = {
    val myListOfTuples = List(("Abhiram", 56), ("Sheena", 33), ("Meena", 50))

    myListOfTuples.foreach(x => if (x._2 > 50) println(x._1 + " passed") else println(x._1 + " failed"))
  }

  @scala.annotation.tailrec
  def insertInteger(): Unit = {
    val inserted = io.StdIn.readInt()
    if (inserted % 2 == 0) println(inserted)
    else if (inserted == 33) return
    insertInteger()
  }
}

