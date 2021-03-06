val Http4sVersion           = "0.21.0-RC2"
val CirceVersion            = "0.12.3"
val MUnitVersion            = "0.4.3"
val LogbackVersion          = "1.2.3"
val KindProjectorVersion    = "0.11.0"
val BetterMonadicForVersion = "0.3.1"

lazy val root = (project in file("."))
  .settings(
    organization := "dev.hugovr",
    name := "compass",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "org.http4s"     %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"     %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"     %% "http4s-circe"        % Http4sVersion,
      "org.http4s"     %% "http4s-dsl"          % Http4sVersion,
      "io.circe"       %% "circe-core"          % CirceVersion,
      "io.circe"       %% "circe-generic"       % CirceVersion,
      "io.circe"       %% "circe-parser"        % CirceVersion,
      "ch.qos.logback" % "logback-classic"      % LogbackVersion,
      "org.scalameta"  %% "munit"               % MUnitVersion % Test
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % KindProjectorVersion cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % BetterMonadicForVersion),
    testFrameworks := List(new TestFramework("munit.Framework"))
  )

inThisBuild(
  List(
    organization := "dev.hugovr",
    homepage := Some(url("https://hugo-vrijswijk.github.com/compass")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "hugo-vrijswijk",
        "Hugo van Rijswijk",
        "hugo.v.rijswijk@gmail.com",
        url("https://github.com/hugo-vrijswijk")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/hugo-vrijswijk/compass"),
        "scm:git:https://github.com/hugo-vrijswijk/compass.git",
        "scm:git:git@github.com:hugo-vrijswijk/compass.git"
      )
    )
  )
)
