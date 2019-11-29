val Http4sVersion           = "0.21.0-M5"
val CirceVersion            = "0.12.3"
val MiniTestVersion         = "2.7.0"
val LogbackVersion          = "1.2.3"
val KindProjectorVersion    = "0.11.0"
val BetterMonadicForVersion = "0.3.1"

lazy val root = (project in file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
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
      "io.monix"       %% "minitest"            % MiniTestVersion % Test
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % KindProjectorVersion cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % BetterMonadicForVersion),
    testFrameworks += new TestFramework("minitest.runner.Framework"),
    guardrailTasks in Compile := List(
      ScalaServer(
        file("../contract/server.yaml"),
        pkg = "compass.generated",
        framework = "http4s",
        modules = List("http4s", "circe")
      )
    ),
    graalVMNativeImageGraalVersion := Some("19.3.0-java11"), // Build from docker
    graalVMNativeImageOptions := Seq(
      "-H:+ReportExceptionStackTraces",
      "--report-unsupported-elements-at-runtime",
      "--allow-incomplete-classpath",
      "--no-fallback",
      "--initialize-at-build-time=ch.qos.logback,org.slf4j,scala.Function4,scala.Symbol"
    )
  )

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-deprecation"
)

inThisBuild(
  List(
    organization := "dev.hugovr",
    homepage := Some(url("https://github.com/hugo-vrijswijk/compass")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "hugo-vrijswijk",
        "Hugo van Rijswijk",
        "hugo.v.rijswijk@gmail.com",
        url("https://github.com/hugo-vrijswijk")
      )
    )
  )
)
