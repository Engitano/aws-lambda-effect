resolvers += Resolver.sonatypeRepo("releases")
val kindProjector = "org.typelevel" % "kind-projector" % "0.10.3" cross CrossVersion.binary

val majorVersion = SettingKey[String]("major version")
val minorVersion = SettingKey[String]("minor version")
val patchVersion = SettingKey[Option[String]]("patch version")

Global / majorVersion := "0"
Global / minorVersion := "1"
Global / patchVersion := Some("0")

val writeVersion = taskKey[Unit]("Writes the version to version.txt")
writeVersion := {
  IO.write(baseDirectory.value / "version.txt", (`aws-lambda-effect` / version).value)
}

test in publish := {}

lazy val `aws-lambda-effect` = (project in file("."))
  .settings(Common())
  .settings(
    version := s"${majorVersion.value}.${minorVersion.value}${patchVersion.value.fold("")(p => s".$p")}",
    libraryDependencies ++= Seq(
      Dependencies.catsEffect,
      Dependencies.circe,
      Dependencies.circeAuto,
      Dependencies.circeFs2,
      Dependencies.fs2io,
      Dependencies.lambda,
      Dependencies.slf4j,
      Dependencies.scalatest           % Test,
      Dependencies.scalamock           % Test,
      Dependencies.catsTestkit         % Test,
      Dependencies.scalacheckShapeless % Test
    )
  )
  .settings(addCompilerPlugin(kindProjector))
