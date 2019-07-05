import bintray.BintrayPlugin.autoImport.{bintrayOrganization, bintrayPackageLabels}
import sbt._
import sbt.Keys.{licenses, _}



object Common {

  val scala213 = "2.13.0"
  val scala212 = "2.12.8"
  val scala211 = "2.11.12"
  val supportedScalaVersions = List(scala213, scala212, scala211)

  def apply() = Seq(
    scalaVersion := scala213,
    organization := "com.engitano",
    organizationName := "Engitano",
    crossScalaVersions := supportedScalaVersions,
    startYear := Some(2019),
    bintrayOrganization := Some("engitano"),
    bintrayPackageLabels := Seq("firestore", "fs2"),
    licenses += ("MIT", new URL("http://opensource.org/licenses/MIT"))
  )
}
