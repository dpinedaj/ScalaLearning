object logicOperators {
  def main(args: Array[String]): Unit = {
    //Use "&&" as "AND" operator
    println(("aardvark" == "aardvark") && ("qokka" == "aardvark"))

    //Use "||" as "OR" operator
    println(("aardvark" == "aardvark" || "qokka" == "aardvark"))

    //Use ! as "NOT" operator
    println(!("aardvark" == "aardvark"))
  
  }
}
