package examples

import examples.{Hello, HelloMessage, Utils, HelloObject}

object testClasses {
  def main(args: Array[String]): Unit = { //The main function is needed to run the code
    val hello = new Hello() //Prints Hello
    val helloMessage = new HelloMessage("Hello World") //Prints Hello World
    helloMessage.myMsg = "Good Bye"
    println(helloMessage.myMsg)

    //to use a class method is needed to instanciate it
    val utils = new Utils()
    utils.echo("Hello world in method!")

    //In an object or a "singletonObject, just call it"
    HelloObject echo("Hello World from singleton") // The class methods ca
  }

}

