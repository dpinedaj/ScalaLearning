package examples.module_1

import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

object SparkVectorsLabel {
  def main(ars: Array[String]): Unit = {

    //Dense vectores can be from an Array of doubles
    val vd: Vector = Vectors.dense(44.0, 0.0, 55.0) //Indices are 0-based integers on 
                                            //a single machine MLlib's vectors can either be dense or sparse.

    //Sparse vector size 3 from 2 arrays, once is the indexes and another are values
    val vs1: Vector = Vectors.sparse(3, Array(0, 2), Array(44.0, 55.0))

    //Sparse vector size 3 from a sequence of tuples, each tuple contains index and value
    val vs2: Vector = Vectors.sparse(3, Seq((0, 44.0), (2, 55.0)))

    //Create a labeled point of 1 (positive) for these vector
    val lp1 = LabeledPoint(1.0, Vectors.dense(44.0, 0.0, 55.0))

    //Create a labeled point of 0 (negative) for these vector
    val lp2 = LabeledPoint(0.0, Vectors.sparse(3, Array(0, 2), Array(44.0, 55.0)))
  }
}
