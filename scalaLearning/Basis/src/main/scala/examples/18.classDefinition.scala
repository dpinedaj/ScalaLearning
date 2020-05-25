package examples

//To define a bassic class

class Hello {
  println("Hello") //When instanciate the class, it will be printed
}

class HelloMessage(message: String = null) {
  //using var instead of val the value can be accessed and replaced
  //in each instance of the class
  val defaultMsg = "hello"
  var myMsg = message
  if (myMsg != null) {
    println(defaultMsg + myMsg)
  } else {
    println(defaultMsg)
  }
}

class Utils {
  def echo(msg: String): Unit = {
    println(msg)
  }
}


//Private vals and companion objects.

//This is defined as a companion object for the class Welcome
object Welcome {
   //When i define a private val, it can't be accessed by
  //The instance, and off course can't be modified
  private val defaultMessage: String = "Welcome!"
  //It can be used like the constructor for his companion class
  
}
class Welcome(message: String = Welcome.defaultMessage) {
  //This class can access to the private val message from his class
  println(message)

  /*The apply method is special and can be called using:
    >>>val example = new Welcome(val1, val2)
    >>>example.apply("Hello", 10)
    Is equal to using:
      >>>example("Hello", 10)*/
  def apply(val1:Int, val2:String=message): String ={
    return s"The int val is: $val1, the default message is: $val2"
  }
}


