package examples.module_5

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

//Feature engineering
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.StringIndexerModel
import org.apache.spark.ml.feature.VectorAssembler

//Random Forest Classifier
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.classification.RandomForestClassificationModel

//Own objects
import DataGrantShow.df

object CreatingFeatures {
  Logger.getLogger("org").setLevel(Level.OFF)
  val dfE = reEncodeData(df)
  val dfS = summarizeTeamData(dfE)
  val indexers = handleCategorical(dfS)
  val dA = defineAssembler
  val dRFC = defineRFC

  def main(args: Array[String]): Unit = {

    println("Re encode some data: ")
    dfE.show(5)

    println("Summaryze and group data by id: ")
    dfS.show(5)

  }

  def reEncodeData(df: DataFrame): DataFrame = {
    val researchers = df
      .withColumn("phd", df("With_PHD").equalTo("Yes").cast("Int"))
      .withColumn("CI", df("Role").equalTo("CHIEF_INVESTIGATOR").cast("Int"))
      .withColumn("paperscore", df("A2") * 4 + df("A") * 3)

    return researchers
  }
  def summarizeTeamData(df: DataFrame): DataFrame = {
    val grants = df
      .groupBy("Grant_Application_ID")
      .agg(
        max("Grant_Status").as("Grant_Status"),
        max("Grant_Category_Code").as("Category_Code"),
        max("Contract_Value_Band").as("Value_Band"),
        sum("phd").as("PHDs"),
        when(max(expr("paperscore * CI")).isNull, 0)
          .otherwise(max(expr("paperscore * CI")))
          .as("paperscore"),
        count("*").as("teamsize"),
        when(sum("Number_of_Successful_Grant").isNull, 0)
          .otherwise(sum("Number_of_Successful_Grant"))
          .as("successes"),
        when(sum("Number_of_Unsuccessful_Grant").isNull, 0)
          .otherwise(sum("Number_of_Unsuccessful_Grant"))
          .as("failures")
      )
    return grants
  }

  def handleCategorical(df: DataFrame): Array[StringIndexerModel] = {
    val value_band_indexer = new StringIndexer()
      .setInputCol("Value_Band")
      .setOutputCol("Value_index")
      .fit(df)

    val category_indexer = new StringIndexer()
      .setInputCol("Category_Code")
      .setOutputCol("Category_index")
      .fit(df)

    val label_indexer = new StringIndexer()
      .setInputCol("Grant_Status")
      .setOutputCol("status")
      .fit(df)

    return Array(value_band_indexer, category_indexer, label_indexer)
  }

  def defineAssembler: VectorAssembler = {
    val assembler = new VectorAssembler()
      .setInputCols(
        Array(
          "Value_index",
          "Category_index",
          "PHDs",
          "paperscore",
          "teamsize",
          "successes",
          "failures"
        )
      )
      .setOutputCol("assembled")
    return assembler
  }

  def defineRFC: RandomForestClassifier = {
    val rf = new RandomForestClassifier()
      .setFeaturesCol("assembled")
      .setLabelCol("status")
      .setSeed(42)
    return rf
  }
}
