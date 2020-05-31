package examples.extra


object PatternMatching {

    //The match *KeyWord*
    def isCustomer(someValue: Any): Boolean = {
        someValue match {
            case cust: Customer => true
            case _ => false
        }
    }
    case class Customer(first: String = "", last: String = "")
    val c = Customer("Martin", "Odersky")

    isCustomer(c) //res0: Boolean = true

    //Pattern Matching an Option
    def getMiddleName(value: Option[String]): String ={
        value match {
            case Some(middleName) => middleName
            case None => "No middle name"
        }
    }
    case class Provider(first: String = "", middleName: Option[String] = None, last: String = "")
    val p = Provider("Martin", last = "Odersky")
    getMiddleName(p.middleName)//res0: String = No middle name

    
}