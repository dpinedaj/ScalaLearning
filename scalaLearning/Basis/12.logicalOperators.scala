object logicOperators {
  def main(args: Array[String]): Unit = {
    //Use "&&" as "AND" operator
    println(("aardvark" == "aardvark") && ("qokka" == "aardvark"))

    //Use "||" as "OR" operator
    println(("aardvark" == "aardvark" || "qokka" == "aardvark"))

    //Use ! as "NOT" operator
    println(!("aardvark" == "aardvark"))

    //match statement allows me to compare to multiple values

    val n = 2
    var a :String = ""
    n match {
      case 1 => a = "Is 1"
      case 2 => a = "Is 2"
      case 3 => a = "Is 3"
    }
    println(a)

    val m: Int = 3
    println(m match {
      case 1 => "Is 1"
      case 2 => "Is 2"
      case 3 => "Is 3"
    })

    //If there's no case for my value, the default value is called by '_'
    val o: Int = 10
    println(o match {
      case 1 => "Is 1"
      case 2 => "Is 2"
      case 3 => "Is 3"
      case _ => "Is not defined"
    })
  
  }
}
