name := "textanalysis"

version := "0.1"

scalaVersion := "2.11.12"


libraryDependencies ++= Seq(
  library.dl4j,
  library.nd4jNativePlatform,
  library.scalNet,
  library.dl4jNlp
)


lazy val library =
  new {

    object Version {
      val dl4j = "1.0.0-alpha"
      val dl4jNlp = "1.0.0-alpha"
      val scalaTest = "3.0.4"
    }

    val dl4j = "org.deeplearning4j" % "deeplearning4j-core" % Version.dl4j
    val nd4jNativePlatform = "org.nd4j" % "nd4j-native-platform" % Version.dl4j
    val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest
    val scalNet = "org.deeplearning4j" %% "scalnet" % Version.dl4j
    val dl4jNlp = "org.deeplearning4j" % "deeplearning4j-nlp" % Version.dl4jNlp
  }