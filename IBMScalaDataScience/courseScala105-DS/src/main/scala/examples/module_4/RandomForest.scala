package examples.module_4

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.SparkContext

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}
import org.apache.spark.sql.functions._

//Libraries to feature flow the data
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.PipelineModel
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}
import org.apache.spark.mllib.util.MLUtils.{
  convertVectorColumnsFromML => fromML,
  convertVectorColumnsToML => toML
}

//Random Forest estimator and model class
import org.apache.spark.ml.classification.RandomForestClassifier
import org.apache.spark.ml.classification.RandomForestClassificationModel

//Random Forest regressor estimator and model class
import org.apache.spark.ml.regression.RandomForestRegressor
import org.apache.spark.ml.regression.RandomForestRegressionModel

object RandomForest {
  Logger.getLogger("org").setLevel(Level.OFF)

  val sc: SparkContext = new SparkContext("local[*]", "DecisionTrees")
  val spark: SparkSession = SparkSession.builder().getOrCreate()
  import spark.implicits._

  def main(args: Array[String]): Unit = {
    try {
      val df = loadData
      println("Initial DataFrame: ")
      df.show()

      val pipelineRF = createRFPipeline(df)
      val Array(trainingData, testData) = df.randomSplit(Array(0.7, 0.3))

      val (pipelineRFCModel, rfcModel) = createRFModel(trainingData, pipelineRF)

      val predictions = pipelineRFCModel.transform(toML(testData))
      println("\nModel feature Importances:")
      println(rfcModel.featureImportances)

      println("\nModel trained: ")
      println(rfcModel.toDebugString)

      println("\nPredictions:")
      predictions.select("predictedLabel", "label", "features").show(5)

      //RandomForest Regression

      val pipelineRFR = createRegressorPipeline(df)
      val pipelineRFRModel = createRegresorRFModel(trainingData, pipelineRFR)

      val predictionsR = pipelineRFRModel.transform(toML(testData))
      println("\nPredictions with Regressor:")
      predictionsR.select("prediction", "label", "features").show(5)

    } finally {
      this.spark.stop()
    }
  }

  def loadData: DataFrame = {
    val data =
      MLUtils.loadLibSVMFile(this.sc, "data/sample_libsvm_data.txt").toDF

    return data
  }

  def createRFPipeline(df: DataFrame): Pipeline = {
    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(df)
    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labels)
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(toML(df))

    val rfC = new RandomForestClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeatures")
      .setNumTrees(3)

    val pipelineRFC = new Pipeline()
      .setStages(Array(labelIndexer, featureIndexer, rfC, labelConverter))
    return pipelineRFC
  }

  def createRFModel(
      df: DataFrame,
      pipeline: Pipeline
  ): (PipelineModel, RandomForestClassificationModel) = {
    val pipelineRFC = pipeline.fit(toML(df))
    val rfModelC =
      pipelineRFC.stages(2).asInstanceOf[RandomForestClassificationModel]
    return (pipelineRFC, rfModelC)
  }

  def createRegressorPipeline(df: DataFrame): Pipeline = {
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(toML(df))
    val rfR = new RandomForestRegressor()
      .setLabelCol("label")
      .setFeaturesCol("indexedFeatures")

    val pipelineRFR = new Pipeline().setStages(Array(featureIndexer, rfR))
    return pipelineRFR

  }
  def createRegresorRFModel(
      df: DataFrame,
      pipeline: Pipeline
  ): PipelineModel = {
    val modelRFR = pipeline.fit(toML(df))
    return modelRFR
  }
}
