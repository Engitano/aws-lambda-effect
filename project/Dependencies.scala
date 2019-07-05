import sbt._
import Keys._

object Dependencies {

  object Versions {
    val catsV      = "2.0.0-M4"
    val circeV     = "0.12.0-M3"
    val circeFs2V  = "0.12.0-M1"
    val lambdaV    = "1.2.0"
    val fs2V       = "1.1.0-M1"
    val slf4jV     = "1.7.25"
    val scalatestV = "3.0.8"
  }

  import Versions._
  val catsEffect = "org.typelevel" %% "cats-effect"         % catsV
  val circe      = "io.circe"      %% "circe-parser"        % circeV
  val circeAuto  = "io.circe"      %% "circe-generic"       % circeV
  val circeFs2   = "io.circe"      %% "circe-fs2"           % circeFs2V
  val fs2io      = "co.fs2"        %% "fs2-io"              % fs2V
  val lambda     = "com.amazonaws" % "aws-lambda-java-core" % lambdaV
  val slf4j      = "org.slf4j"     % "slf4j-api"            % slf4jV

  val scalatest           = "org.scalatest"              %% "scalatest"                 % scalatestV
  val scalamock           = "org.scalamock"              %% "scalamock"                 % "4.3.0"
  val catsTestkit         = "org.typelevel"              %% "cats-testkit"              % catsV
  val scalacheckShapeless = "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.3"
}
