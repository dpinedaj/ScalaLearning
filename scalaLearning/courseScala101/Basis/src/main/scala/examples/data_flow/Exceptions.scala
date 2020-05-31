package examples.data_flow

object Exceptions {

    /* The basic structure is:
        try {
            <some operation>
        } catch {
            case a : <someException> => <someResult>
            case _ => <someException => <someResult>
        }*/


    def toInt(s: String): Int = {
        try {
            s.toInt
        } catch {
            case _: NumberFormatException => 0
        }
    }

    //Wrapping a Call in Try
    import scala.util.{Try, Success, Failure}
    Try("100".toInt) //res0: Success(100)
    Try("Martin".toInt)//res0: scala.util.Try[Int] = Failure(java.lang.NumberFormatException:
                      //                                        For input string: "Martin")

    //Using the Failure and Sucess in a method
    def makeInt(s: String): Int = {
        Try(s.toInt) match {
            case Success(n) => n;
            case Failure(_) => 0
        }
    }

    makeInt("35")//res0: Int = 35
    makeInt("Martin")//res0: Int = 0

    
}