package RockJVM

object Traits extends App{

  trait Talker(subject: String) {
    def talkTo(another: Talker): String = ""
  }

  class Person(name: String) extends Talker("rock music") {
    override def talkTo(another: Talker): String = ""
  }

  class RockFan extends Talker("rock music")
  class RockFanatic extends RockFan with Talker // no constructor argument in the 2nd mixin


  // derived traits will NOT pass constructor arguments to parent traits
  trait BrokenRecord extends Talker

  class AnnonyingFriend extends BrokenRecord with Talker("politics")


  // super traits
  trait Color
  case object Red extends Color
  case object Green extends Color
  case object Blue extends Color

  val color = if (43 > 2) Red else Blue // Color with Product with Serializable
}
