import sbt._
import Keys._

object Dependencies {

  object Versions {
    val catsV      = "1.3.1"
    val circeV     = "0.11.0"
    val lambdaV    = "1.2.0"
    val fs2V       = "1.0.5"
    val slf4jV     = "1.7.25"
    val scalatestV = "3.0.7"
    val scalaMockV = "4.1.0"
  }

  import Versions._
  val catsEffect = "org.typelevel" %% "cats-effect"         % catsV
  val circe      = "io.circe"      %% "circe-parser"        % circeV
  val circeAuto  = "io.circe"      %% "circe-generic"       % circeV
  val circeFs2   = "io.circe"      %% "circe-fs2"           % circeV
  val fs2io      = "co.fs2"        %% "fs2-io"              % fs2V
  val lambda     = "com.amazonaws" % "aws-lambda-java-core" % lambdaV
  val slf4j      = "org.slf4j"     % "slf4j-api"            % slf4jV

  val scalatest           = "org.scalatest"              %% "scalatest"                 % scalatestV
  val scalaMock           = "org.scalamock"              %% "scalamock"                 % scalaMockV
  val catsTestkit         = "org.typelevel"              %% "cats-testkit"              % "1.6.1"
  val scalacheckShapeless = "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.8"
}
