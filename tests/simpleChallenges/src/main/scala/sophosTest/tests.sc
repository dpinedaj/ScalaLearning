
import util.control.Breaks.{breakable, break}

val a: String = "hello"
def getDouble(x:String): Boolean = {
  try{
    x.toDouble
    true
  }catch{
    case _: Throwable => false
  }
}
getDouble(a)


val text = "Python"
val n = 3
text.substring(0,3)

val myList = List(1,1,1,2,3,4,12,23,3,3,3,2,2,2)
val s = myList.toSet.toList




