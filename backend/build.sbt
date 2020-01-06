val Http4sVersion           = "0.21.0-M6"
val CirceVersion            = "0.12.3"
val MiniTestVersion         = "2.7.0"
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
      "io.monix"       %% "minitest"            % MiniTestVersion % Test
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % KindProjectorVersion cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % BetterMonadicForVersion),
    testFrameworks += new TestFramework("minitest.runner.Framework"),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-language:higherKinds",
      "-language:postfixOps",
      "-feature",
      "-deprecation"
    ),
    guardrailTasks in Compile := List(
      ScalaServer(
        file("../contract/server.yaml"),
        pkg = "compass.generated",
        framework = "http4s",
        modules = List("http4s", "circe")
      )
    )
  )

lazy val docs = (project in file("docs"))
  .settings(
    micrositeGithubOwner := "hugo-vrijswijk",
    micrositeGithubRepo := "compass",
    micrositeUrl := s"https://${micrositeGithubOwner.value}.github.io",
    micrositeBaseUrl := s"/${micrositeGithubRepo.value}",
    micrositeDocumentationUrl := s"${micrositeBaseUrl.value}/docs",
    micrositeAnalyticsToken := "UA-155360939-1",
    micrositeGitterChannel := true,
    micrositeShareOnSocial := true,
    micrositePushSiteWith := GitHub4s,
    micrositeGithubToken := sys.env.get("GITHUB_TOKEN")
  )
  .enablePlugins(MicrositesPlugin)

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
