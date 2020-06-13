package examples.module_1

import org.apache.log4j.Logger
import org.apache.log4j.Level




import org.apache.spark.SparkContext

import org.apache.spark.mllib.linalg.{Matrix, Matrices}

//Imports for rowMatrices
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.mllib.linalg.{Vector, Vectors}

//Imports for IndexedMatrices
import org.apache.spark.mllib.linalg.distributed.{
  IndexedRow,
  IndexedRowMatrix,
  RowMatrix
}

//Imports for coordinated matrices
import org.apache.spark.mllib.linalg.distributed.MatrixEntry
import org.apache.spark.mllib.linalg.distributed.CoordinateMatrix

object SparkMatrices {

  Logger.getLogger("org").setLevel(Level.OFF)
  
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[*]", "SparkMatrices")

    //Dense Matrices are "reshaped" dense Vector
    //First two arguments specify dimensions, (rows, columns)
    //The first 3 values are the first column
    //And the next 3 are the second column
    try {
      LocalMatricesExample.run
      RowMatricesExample.run(sc)
      IndexedMatricesExample.run(sc)
      CoordinateMatricesExample.run(sc)
    } finally {
      sc.stop()
    }
  }
}

object LocalMatricesExample {
  def run = {
    val md: Matrix = Matrices.dense(3, 2, Array(1, 3, 5, 2, 4, 6))
    println("\nLocal Dense Matrix")
    println(md)
    //Compressed Sparse Column Matrix (CSC)

    val rows = 5
    val columns = 4
    val columnPointers = Array(0, 0, 1, 2, 2) /*Each non-zero values to the column it belongs in,
                                            The first value is always 0, for beign counting from index 0

                                            The last value is always the total number of non-zero values*/

    val rowsIn = Array(1, 3) // The rows where the values were in
    val nonZeroValues = Array(34.0, 55.0) // represent the values different to zero in the matrix

    val mcsc =
      Matrices.sparse(
        rows,
        columns,
        columnPointers,
        rowsIn,
        nonZeroValues
      )
    println("\nCSC Matrix")
    println(mcsc)
  }
}
object RowMatricesExample {

  def run(sc: SparkContext) = {

    val row1 = Vectors.dense(1.0, 2.0)
    val row2 = Vectors.dense(4.0, 5.0)
    val row3 = Vectors.dense(7.0, 8.0)

    val rows: RDD[Vector] = sc.parallelize(
      Array(
        row1,
        row2,
        row3
      )
    )
    val mat: RowMatrix = new RowMatrix(rows)
    println("\nRowMatrix")
    mat.rows.collect().foreach(println)
    val m = mat.numRows() // m: Long = 3
    val n = mat.numCols() // n: Long = 2
  }
}

object IndexedMatricesExample {

  def run(sc: SparkContext) = {
    val rows: RDD[IndexedRow] = sc.parallelize(
      Array(
        IndexedRow(0, Vectors.dense(1.0, 2.0)),
        IndexedRow(1, Vectors.dense(4.0, 5.0)),
        IndexedRow(2, Vectors.dense(7.0, 8.0))
      )
    )

    val idxMat: IndexedRowMatrix = new IndexedRowMatrix(rows)
    println("\nIndexedRowMatrix")
    idxMat.rows.collect().foreach(println)
  }
}

object CoordinateMatricesExample {
  def run(sc: SparkContext) = {
    val entries: RDD[MatrixEntry] = sc.parallelize(
      Array(
        MatrixEntry(0, 0, 9.0),
        MatrixEntry(1, 1, 8.0),
        MatrixEntry(2, 1, 6.0)
      )
    )

    val coordMat: CoordinateMatrix = new CoordinateMatrix(entries)
    println("\nCoordinateMatrix")
    coordMat.entries.collect().foreach(println)
  }
}
