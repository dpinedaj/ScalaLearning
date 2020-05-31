package examples.collections


object Obtions {
    //An option represent the existence of a value
    val opt = Option("Jamie")//res0: Option[String] = Some(Jamie)
    println(opt.get) //res0: String = Jamie

    //To ensure the existence of the value without error
    println(opt.getOrElse("Jamie"))// res0: String = Jamie 

    //The option Null cant be accessed by get
    val none = Option(null) //res0:Option[Null] = None
    

}