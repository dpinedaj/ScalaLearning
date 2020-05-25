package examples

case object exampleCaseObject



object testCaseObjects{
    def main(args: Array[String]):Unit = {

        val caseObj = exampleCaseObject
        println(caseObj.toString)
    }
}