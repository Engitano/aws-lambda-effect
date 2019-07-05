import sbt._
import sbt.Keys.{licenses, _}

object Common {

  val scalaV211 = "2.11.12"
  val scalaV212 = "2.12.8"

  def settings(): Seq[Def.Setting[_]] = Seq(
    scalaVersion := scalaV212,
    version := "0.1",
    crossScalaVersions := Seq(scalaV211, scalaV212),
    licenses += ("MIT", new URL("http://opensource.org/licenses/MIT")),
  )
}
