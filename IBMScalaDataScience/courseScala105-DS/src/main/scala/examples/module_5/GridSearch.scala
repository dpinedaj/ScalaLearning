package examples.module_5

import org.apache.spark.ml.tuning.CrossValidator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.ml.param.ParamMap

import CreatingFeatures.dRFC
import CreatePipeline.{pipeline, aucEval, training, test}

object GridSearch {

  val cvGrid = defineParamGrid
  val cv = defineCrossValidator

  val cvModel = cv.fit(training)
  val cv_results = cvModel.transform(test)
  def main(args: Array[String]): Unit = {
    println("Results after grid search:" + aucEval.evaluate(cv_results))
  }

  def defineParamGrid: Array[ParamMap] = {
    val paramGrid = new ParamGridBuilder()
      .addGrid(dRFC.maxDepth, Array(2, 5))
      .addGrid(dRFC.numTrees, Array(1, 20))
      .build()
    return paramGrid
  }
  def defineCrossValidator: CrossValidator = {
    val cv = new CrossValidator()
      .setEstimator(pipeline)
      .setEvaluator(aucEval)
      .setEstimatorParamMaps(cvGrid)
      .setNumFolds(3)
    return cv
  }
}
