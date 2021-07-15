package RockJVM

object NewTypes extends App{

  // 1 - literal types
  val aNumber = 3  // integer by default
  val three: 3 = 3 // new type 3 as subtype of int --->   3 <: Int


  def passNumber(n: Int) = println(n)
  passNumber(45)
  passNumber(three) //Is fine because 3 is a subtype of Int

  def passStrict(n: 3) = println(3)
  passStrict(three)
  // passStrict(45) //Is not possible

  // different sub types
  val pi: 3.14 = 3.14
  val truth: true = true
  val myFavoriteLanguage: "Scala" = "Scala"


  def doSomethingWithYourLife(meaning: Option[42]) = meaning.foreach(println)


  // 2 - Union types

  def ambivalentMethod(arg: String | Int) = arg match {
    case _: String => println(s"a string: $arg")
    case _: Int => println(s"a Int: $arg")
  }

  ambivalentMethod(42)
  ambivalentMethod("Scala")

  // Return something if there is not the type expected
  trait File
  type ErrorOr[T] = T | "error"
  def handleResource(file: ErrorOr[File]): Unit = {
    ()
  }

  val stringOrInt = if (43 > 0 ) "a string" else 43
  val aStringOrInt: String | Int = if (43 > 0 ) "a string" else 43

  // 3 - intersection types
  trait Camera {
    def takePhoto() = println("snap")
    def use() = println("using Camera")
  }

  trait Phone {
    def use() = println("ring")
  }

  def useSmartDevice(sp: Camera & Phone) = {
    sp.use()
    sp.takePhoto()
  }

  class Smartphone extends Camera with Phone {
    override def use(): Unit = println("smart")
  }
  useSmartDevice(new Smartphone)


  trait HostConfig
  trait HostController {
    def get: Option[HostConfig]
  }

  trait PortConfig
  trait PortController {
    def get: Option[PortConfig]
  }

  def getConfigs(controller: HostController & PortController): Option[HostConfig & PortConfig] = controller.get
}

