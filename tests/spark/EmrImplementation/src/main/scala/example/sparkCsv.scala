package example

import java.util.logging.{Level, Logger}
import org.apache.spark.sql.SparkSession

class exampleCsv {
  Logger.getLogger("org").setLevel(Level.INFO)
  def get_session(appName: String): SparkSession = {
    
    val session = SparkSession
      .builder()
      .appName("exampleCsv")
      .master("local[1]")
      .getOrCreate()
    return session
  }
  def create_dataframe(session: SparkSession, path: String): org.apache.spark.sql.DataFrame = {

    val dataframe = session.read
      .option("header", "true")
      .option("inferSchema", value = true)
      .csv(path) //"s3n://testbucket/survey_results_public.csv")// Data source: https://insights.stackoverflow.com/survey

     return dataframe
  }
}
