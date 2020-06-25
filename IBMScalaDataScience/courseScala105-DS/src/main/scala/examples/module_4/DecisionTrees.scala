package examples.module_4

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions._

//Classification Estimator and Model generated to define statically
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.PipelineModel

//Regressor Estimator and Model generated to define statically
import org.apache.spark.ml.regression.DecisionTreeRegressor
import org.apache.spark.ml.regression.DecisionTreeRegressionModel

//Libraries to feature flow the data
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}
import org.apache.spark.mllib.util.MLUtils.{
  convertVectorColumnsFromML => fromML,
  convertVectorColumnsToML => toML
}

object DecisionTrees {
  Logger.getLogger("org").setLevel(Level.OFF)
  val sc: SparkContext = new SparkContext("local[*]", "DecisionTrees")
  val spark: SparkSession = SparkSession.builder().getOrCreate()
  import spark.implicits._
  def main(args: Array[String]): Unit = {

    try {
      val df = loadData
      println("Initial DataFrame")
      df.show()
      val (trainDf, testDf) = splitData(df)

      //Classification
      val treePipeline = createTreePipeline(df)

      val (pipeline, model) = createTreeModel(trainDf, treePipeline)
      println("\nClassification model:\n" + model.toDebugString)

      val predictions = pipeline.transform(testDf)
      println("\nPredictions: ")
      predictions.show()

      //Regression
      val treePipelineReg = createRegressorPipeline(df)
      val (pipelineReg, modelReg) =
        createRegressorModel(trainDf, treePipelineReg)
      println("\nRegression model: \n" + modelReg.toDebugString)

      val predictionsReg = pipelineReg.transform(testDf)
      println("\nRegression predictions: ")
      predictionsReg.show()

    } finally {
      this.spark.stop()
      this.sc.stop()
    }
  }

  def loadData: DataFrame = {
    val data = toML(
      MLUtils.loadLibSVMFile(this.sc, "data/sample_libsvm_data.txt").toDF
    )
    return data
  }

  def createTreePipeline(df: DataFrame): Pipeline = {
    val dtC = new DecisionTreeClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeatures")

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
      .fit(df)
    val pipelineClass = new Pipeline()
      .setStages(Array(labelIndexer, featureIndexer, dtC, labelConverter))
    return pipelineClass
  }

  def splitData(df: DataFrame): (DataFrame, DataFrame) = {
    val Array(trainingData, testData) = df.randomSplit(Array(0.7, 0.3))
    return (trainingData, testData)
  }

  def createTreeModel(
      df: DataFrame,
      pipeline: Pipeline
  ): (PipelineModel, DecisionTreeClassificationModel) = {
    val modelClassifier = pipeline.fit(df)
    val treeModel =
      modelClassifier.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    return (modelClassifier, treeModel)
  }

  def createRegressorPipeline(df: DataFrame): Pipeline = {
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(df)
    val dtR = new DecisionTreeRegressor()
      .setLabelCol("label")
      .setFeaturesCol("indexedFeatures")

    val pipelineReg = new Pipeline().setStages(Array(featureIndexer, dtR))
    return pipelineReg
  }
  def createRegressorModel(
      df: DataFrame,
      pipeline: Pipeline
  ): (PipelineModel, DecisionTreeRegressionModel) = {
    val modelRegressor = pipeline.fit(df)

    val treeModel =
      modelRegressor.stages(1).asInstanceOf[DecisionTreeRegressionModel]
    return (modelRegressor, treeModel)
  }
}
