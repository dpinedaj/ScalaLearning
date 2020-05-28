package examples



object Futures {

    /*To init a future, is needed to use a thread pool to perform the work
    The thread pool will be used inside an execution context */

    import scala.concurrent.ExecutionContext//Library needed to make an execution context
    import java.util.concurrent.ForkJoinPool//LIbrary to define the thread pool

    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor( new ForkJoinPool())

    //can be defined manually a specific timeout
    //Import required imports
    import scala.language.postfixOps //To allow postfix like second
    import scala.concurrent.duration._ //To set the timeout duration
    import scala.concurrent.Future
    import scala.util.Failure
    import scala.util.Success


    implicit val timeout = 1 second //res0: scala.concurrent.duration.FiniteDuration = 1 second

    //Wrapping a Call in a Future
    val f: Future[Int] = Future { 1 + 2 + 3} //f: scala.concurrent.Future[Int] = 
                                            //scala.concurrent.impl.Promise$DefaultPromise@8b96fde
    f.onComplete { 
        case Success(i) => println("onComplete success: " + i);
        case Failure(f) => println("onComplete failure: " + f)
    }
    
    val g: Future[Int] = Future { 1 + 2 + 3}//g:scala.concurrent.Future[Int] = ...
    g.map(result => println("Mapping: " + result))
    
    println("CODE BEGINS HERE")
    for (i <- 0 until 10) println(i)
    
}