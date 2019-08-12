val Http4sVersion           = "0.21.0-M3"
val CirceVersion            = "0.12.0-M1"
val MiniTestVersion         = "2.5.0"
val LogbackVersion          = "1.2.3"
val KindProjectorVersion    = "0.10.3"
val BetterMonadicForVersion = "0.3.1"

val contractBase = file("../contract")
lazy val root = (project in file("."))
  .settings(
    organization := "dev.hugovr",
    name := "compass-backend",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      "org.http4s"     %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"     %% "http4s-blaze-client" % Http4sVersion,
      "org.http4s"     %% "http4s-circe"        % Http4sVersion,
      "org.http4s"     %% "http4s-dsl"          % Http4sVersion,
      "io.circe"       %% "circe-core"          % CirceVersion,
      "io.circe"       %% "circe-generic"       % CirceVersion,
      "io.circe"       %% "circe-parser"        % CirceVersion,
      "io.circe"       %% "circe-java8"         % CirceVersion,
      "ch.qos.logback" % "logback-classic"      % LogbackVersion,
      "io.monix"       %% "minitest"            % MiniTestVersion % Test
    ),
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % KindProjectorVersion
    ),
    addCompilerPlugin(
      "com.olegpy" %% "better-monadic-for" % BetterMonadicForVersion
    ),
    testFrameworks += new TestFramework("minitest.runner.Framework"),
    guardrailTasks in Compile := List(
      ScalaServer(
        contractBase / "server.yaml",
        pkg = "dev.hugovr.compass.generated",
        framework = "http4s"
      )
    )
  )

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature"
)
