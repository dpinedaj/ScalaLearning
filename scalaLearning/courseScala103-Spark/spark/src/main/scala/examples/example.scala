package examples

object example {
    def main(args: Array[String]): Unit = {

        val listy = List(List(1,2,3), List(1,2,3, 1, 3), List(5,6,7))
        for (i <- 0 until listy.length ){
            println(listy(i))
        }
    }
}