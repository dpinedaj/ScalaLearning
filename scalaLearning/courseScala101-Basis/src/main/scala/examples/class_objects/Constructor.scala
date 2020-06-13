package examples.class_objects

class Constructor(empId: Int, name: String, salary: Double) {

  def this(salary: Double) {
    this(0, "", salary)
    println(this.salary)
    println("Zero-argument auxiliary constructor")
  }
  println("Primary constructor")
  println(this.salary)

  val a = this.something("Hola") 

  def something(name: String): Unit = {
    println(s"This is the method name: $name")
    println(s"This is the class name: ${this.name}")
  }
}

object Constructor extends App {
  val cons = new Constructor(200) // The auxiliar constructor will initialize the variables
  val cons2 = new Constructor(1, "Daniel", 500)
  cons2.something("Catalina")
}
