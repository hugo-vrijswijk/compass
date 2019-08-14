val Http4sVersion           = "0.20.9"
val CirceVersion            = "0.11.1"
val MiniTestVersion         = "2.5.0"
val LogbackVersion          = "1.2.3"
val KindProjectorVersion    = "0.10.3"
val BetterMonadicForVersion = "0.3.1"

lazy val root = (project in file("."))
  .settings(
    organization := "dev.hugovr",
    name := "compass",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.9",
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
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % KindProjectorVersion),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % BetterMonadicForVersion),
    testFrameworks += new TestFramework("minitest.runner.Framework"),
    guardrailTasks in Compile := List(
      ScalaServer(
        file("../contract/server.yaml"),
        pkg = "compass.generated",
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
