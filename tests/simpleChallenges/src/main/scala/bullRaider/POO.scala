package bullRaider

object POO extends App {

  val rec = new Rectangle(15.0, 20.0)
  rec.calculateArea()

  class Rectangle(height: Double, width: Double) {
    override def toString: String = "Rectangle height: " + height.toString + " width: " + width.toString

    def calculateArea(): Unit = {
      println(height * width)
    }
  }


}
