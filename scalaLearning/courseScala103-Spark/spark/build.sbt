name := "Simple Spark Project"
version := "1.0"
scalaVersion := "2.12.11"

//Spark modules
val sparkCore = "org.apache.spark" %% "spark-core" % "2.4.0"
val sparkSql = "org.apache.spark" %% "spark-sql" % "2.4.0"
val sparkMlLib = "org.apache.spark" %% "spark-mllib-local" % "2.4.0"

//define main project
lazy val main = (project in file("."))
  .aggregate(utils)
  .dependsOn(utils)
  .settings(
    name := "main",
    libraryDependencies ++= Seq(sparkCore, sparkSql, sparkMlLib),

  )
  
//define utils
lazy val utils = (project in file("utils"))
    .settings(
        name := "utils"
    )