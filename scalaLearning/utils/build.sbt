ThisBuild / scalaVersion := "2.12.11"
name := "Utils"

//Spark modules
val sparkCore = "org.apache.spark" %% "spark-core" % "2.4.0"
val sparkSql = "org.apache.spark" %% "spark-sql" % "2.4.0"
val sparkMlLib = "org.apache.spark" %% "spark-mllib-local" % "2.4.0"

//For test with scala
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1"
val gigahorse = "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0"
val playJson  = "com.typesafe.play" %% "play-json" % "2.8.1"


//define projects
lazy val main = (project in file("."))
  .aggregate(scrappy, spark, general)
  .dependsOn(scrappy, spark, general)
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(
    name := "main",
    libraryDependencies += scalaTest % Test,
  )

lazy val scrappy = (project in file("scrappy"))
  .settings(
    name := "scrappy",
    libraryDependencies ++= Seq(gigahorse, playJson),
    libraryDependencies += scalaTest % Test,
  )

lazy val spark = (project in file("spark"))
  .settings(
    name := "spark",
    libraryDependencies += scalaTest %Test,
  )

lazy val general = (project in file("general"))
  .settings(
    name :="general"
  )