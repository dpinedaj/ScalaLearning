package examples

import examples.{Hello, HelloMessage,
   Utils, HelloObject, ExampleTrait,
  exampleCaseClass, Welcome}

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


    //Using companion object with class
    val welcome = new Welcome()//When initialize the instance, it takes the default message from
                              // his companion object
    println(welcome(1))
  }

  



}
