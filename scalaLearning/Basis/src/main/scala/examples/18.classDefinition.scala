package examples

//To define a bassic class

class Hello {
  println("Hello") //When instanciate the class, it will be printed
}

class HelloMessage(message: String = null) {

  var myMsg = message
  if (myMsg != null) {
    println(myMsg)
  } else {
    println("There's not message")
  }
}

class Utils {
  def echo(msg: String): Unit = {
    println(msg)
  }
}


object HelloObject {

    def echo(msg: String): Unit = {
        println(msg)
    }
}