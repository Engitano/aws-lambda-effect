import bintray.BintrayPlugin.autoImport.{bintrayOrganization, bintrayPackageLabels}
import sbt._
import sbt.Keys.{licenses, _}

object Common {
  def apply() = Seq(
    scalaVersion := "2.12.8",
    organization := "com.engitano",
    organizationName := "Engitano",
    startYear := Some(2019),
    bintrayOrganization := Some("engitano"),
    bintrayPackageLabels := Seq("firestore", "fs2"),
    licenses += ("MIT", new URL("http://opensource.org/licenses/MIT")),
    scalacOptions := Seq(
      "-Ypartial-unification"
    )
  )

}
