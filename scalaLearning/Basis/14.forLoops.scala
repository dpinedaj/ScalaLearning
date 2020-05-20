object foorLoops {
  def main(args: Array[String]): Unit = {

    //walking through a list
    val badgers = List("mushrooms", "mushrooms", "it's a snake")
    //for loop can be just in the line
    for (item <- badgers) println(item)

    //looping through a range
    for (i <- Array.range(0,badgers.size)){
        println(s"$i -> ${badgers(i)}")
    }
    //looping through a range explicit
    for (i <- 0 until badgers.size){
        println(s"$i -> ${badgers(i)}")
    }

    //Looping with if statement
    println("\nfoor loop for even numbers")
    for (i <- Range(0,10)){
        //Note: can be called as Range or Array.range
        if(i %2 == 0){
            println(s"$i is even")
        }else{
            println(s"$i is odd")
        }
    }
  }
}
