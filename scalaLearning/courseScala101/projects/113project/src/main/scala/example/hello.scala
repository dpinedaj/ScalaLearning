package example

import scala.collection.mutable

object Hello {

    val a = mutable.Set(1,2,3)
    def main(args: Array[String]): Unit = {
        a += 10
        println(s"$a")
        
    }

}