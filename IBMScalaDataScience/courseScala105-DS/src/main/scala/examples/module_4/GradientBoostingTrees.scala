package examples.module_4

import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.SparkContext

import org.apache.spark.sql.functions._
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.util.MLUtils.{
  convertVectorColumnsFromML => fromML,
  convertVectorColumnsToML => toML
}

//Modules to perform gradientBoss and Pipeline
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.classification.{
  GBTClassificationModel,
  GBTClassifier
}
import org.apache.spark.ml.feature.{StringIndexer, IndexToString, VectorIndexer}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//Regressor
import org.apache.spark.ml.regression.GBTRegressor
import org.apache.spark.ml.regression.GBTRegressionModel

object GradientBoostingTrees {
  Logger.getLogger("org").setLevel(Level.OFF)
  val sc: SparkContext = new SparkContext("local[*]", "GradientBoost")
  val spark: SparkSession = SparkSession.builder().getOrCreate()
  import spark.implicits._

  def main(args: Array[String]): Unit = {

    try {
      val df = loadData
      println("\nInitial data: ")
      df.show(5)

      val Array(train, test) = df.randomSplit(Array(0.7, 0.3))

      val pipelineGBC = createPipelineGBC(df)

      val modelGBC = createGBModel(train, pipelineGBC)

      val predictions = modelGBC.transform(test)
      println("\nPredictions with Classifier:")
      predictions.select("predictedLabel", "label", "features").show(5)

      // Select (prediction, true label) and compute test error.
      val evaluator = new MulticlassClassificationEvaluator()
        .setLabelCol("indexedLabel")
        .setPredictionCol("prediction")
        .setMetricName("accuracy")

      val accuracy = evaluator.evaluate(predictions)
      println("Test Error = " + (1.0 - accuracy))

      //Gradient Boost Regressor:

      val pipelineGBR = createPipelineGBR(df)

      val modelGBR = createGBModel(train, pipelineGBR)

      val predictionsR = modelGBR.transform(test)
      println("\nPredictions with Regressor:")
      predictionsR.show(5)

    } finally {
      this.sc.stop()
      this.spark.stop()
    }

  }

  def loadData: DataFrame = {
    val data = toML(
      MLUtils
        .loadLibSVMFile(this.sc, "data/sample_libsvm_data.txt")
        .toDF()
    )
    return data

  }

  def createPipelineGBC(df: DataFrame): Pipeline = {
    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(df)

    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(df)

    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labels)

    val gbt = new GBTClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeatures")
      .setMaxIter(10)

    val pipeline = new Pipeline()
      .setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))
    return pipeline
  }

  def createGBModel(df: DataFrame, pipeline: Pipeline): PipelineModel = {
    // Train model. This also runs the indexers.
    val model = pipeline.fit(df)
    return model
  }

  def createPipelineGBR(df: DataFrame): Pipeline = {
    val featureIndexer = new VectorIndexer()
      .setInputCol("features")
      .setOutputCol("indexedFeatures")
      .setMaxCategories(4)
      .fit(df)
    val gbtR = new GBTRegressor()
      .setLabelCol("label")
      .setFeaturesCol("indexedFeatures")
      .setMaxIter(10)

    val pipelineGBTR = new Pipeline().setStages(Array(featureIndexer, gbtR))

    return pipelineGBTR
  }

}
