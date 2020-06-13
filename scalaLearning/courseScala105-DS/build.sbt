import sbt.complete.Parsers._

name := "Simple Spark Project"
version := "1.0"
scalaVersion := "2.12.11"
val sparkVersion = "2.4.0"

//Spark modules
val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion withSources ()
val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion withSources ()
val sparkMlLib =
  "org.apache.spark" %% "spark-mllib" % sparkVersion withSources ()

 //define main project
lazy val main = (project in file("."))
  .aggregate(utils)
  .dependsOn(utils)
  .settings(
    name := "main",
    libraryDependencies ++= Seq(
      sparkCore,
      sparkSql,
      sparkMlLib
    )
  )

//define utils
lazy val utils = (project in file("utils"))
  .settings(
    name := "utils"
  )
