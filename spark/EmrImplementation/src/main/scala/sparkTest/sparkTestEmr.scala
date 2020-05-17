package sparkTest

import java.util.logging.{Level, Logger}
import org.apache.spark.sql.SparkSession

object StackOverFlowSurvey {
  
  def main(args: Array[String]) = {

    Logger.getLogger("org").setLevel(Level.INFO)
    val session = SparkSession
      .builder()
      .appName("StackOverFlowSurvey")
      .master("local[1]")
      .getOrCreate()

    val dataframe = session.read
      .option("header", "true")
      .option("inferSchema", value = true)
      .csv("s3n://testbucket/survey_results_public.csv")// Data source: https://insights.stackoverflow.com/survey

    System.out.println("==Print out schema==")
    dataframe.printSchema()
    
    System.out.println("==Print out some data==")
    dataframe.select("Respondent", "Country").show()


  }
}
