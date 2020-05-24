package examples

import examples.{Hello, HelloMessage,
   Utils, HelloObject, ExampleTrait,
  exampleCaseClass}

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
    HelloObject echo ("Hello World from singleton") // The class methods ca
    

    //Using trait as abstract classes or to inherit methods
    class ExampleWithTrait extends ExampleTrait
    val ex = new ExampleWithTrait()
    ex.print("Hello from Trait")


    //using case class
    val excase = exampleCaseClass(9, 0)

    println(excase.hours)
  }

  //The apply method is special and can be called using:
  // singlethonTest(val1, val2)
  def apply(val1:String, val2:Int): String ={
    return s"The string val is: $val1, the int val is: $val2"
  }



}
