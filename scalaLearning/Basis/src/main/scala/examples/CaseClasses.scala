package examples

/*
In general Case classes are used to represent data
When define a case class with parameters, the parameters are public
Can be compared 2 instances to check if are the same or different
Can copy an instance to anotherone and receive all parameters or change any of them
 */
case class exampleCaseClass(hours: Int = 0, minutes: Int = 0)
//The case classes automatically generates the "apply" method

//Examples
object CaseClasses {

  val caseClass = exampleCaseClass(10, 9)
  val caseClass2 = exampleCaseClass(10, 9)
  //Comparing instances
  println(caseClass == caseClass2) //true

  //It will receive by default the not defined arguments from the previous instance
  val caseClass3 = caseClass2.copy(hours = 10)
  println(caseClass3.minutes)
}


//can be defined a class and a companion object to define the class
class OffTime(val name: String, val lunchTime: String , val breakTime: String)

object OffTime {
  def apply(Name:String, LunchTime : String, BreakTime: String): OffTime =
      new OffTime(Name, LunchTime, BreakTime)
}
//Also this companion object with class can be resumed using a case class (in this case just as data class)
case class OffTime1(name:String, lunchTime:String, breakTime:String)
