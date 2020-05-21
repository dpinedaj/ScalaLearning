import example.exampleCsv
import org.apache.spark.sql.functions._
object csvTest {

  def main(args: Array[String]): Unit = {

    val sparkCsv = new exampleCsv
    val session = sparkCsv.get_session("testCsv")
    val df = sparkCsv.create_dataframe(session, "docs/1.csv")
    println(s"There are ${df.count()} registers")
    
    println("Show the values which LOAN AMOUNT is higher than 10000")
    val dfHighLoan = df.filter(col("LOAN_AMOUNT") > 10000)
    dfHighLoan.show()
  }
}
