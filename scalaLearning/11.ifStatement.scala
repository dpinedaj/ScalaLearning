
object ifstatement {
    /* With logical operators in scala
    we follow the "if" -> "else if" -> "else"
    line of logic*/
    def main(args: Array[String]) = {
        var animals = List("aardvark", "hedgehog", "walrus")

        //if statement #1
        if(
            (animals.contains("aardvark") && animals.contains("walrus"))
        ) {
            println("#1: Aardvarkwalrus!")
        }

        //if - elseif
        //if statement #2
        if (
            (animals.contains("aardvark") && animals.contains("rinhos"))

        ){
            println("#2: Aardvarkocerous!")
        } else if(
            (animals.contains("aardvark") || animals.contains("rinhos"))
        ){
            println("#2: Aardvark or Rhino?")
        }

        //if -elseif -else
        //if statement #3
        if(
            (animals.contains("penguin") && animals.contains("rihno"))
        ){
            println("#3: penhuarhino!")
        }else if(
            (animals.contains("penguin") || animals.contains("rhino"))
        ){
            println("#3: penguin or rinho?")
        }else{
            println("#3: Welcome to Marmot land!")
        }

    }
}