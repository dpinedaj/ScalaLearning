import sbt.complete.Parsers._

ThisBuild / scalaVersion := "2.12.11"
name := "Utils"
val sparkVersion = "2.4.0"
//Spark modules
val sparkCore = "org.apache.spark"  %% "spark-core"         % sparkVersion withSources()
val sparkSql = "org.apache.spark"   %% "spark-sql"          % sparkVersion withSources()
val sparkMlLib = "org.apache.spark" %% "spark-mllib-local"  % sparkVersion withSources()

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
    libraryDependencies ++= Seq(sparkCore, sparkSql, sparkMlLib)
    libraryDependencies += scalaTest %Test,
  )

lazy val general = (project in file("general"))
  .settings(
    name :="general"
  )