

val theapp = crossProject.settings(
  name := "theapp",
  scalaVersion := "2.11.6",
  version := "0.1.0",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature", "-Xlint", "-language:postfixOps"),
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.3.1" % "test",
    "com.lihaoyi" %%% "scalatags" % "0.4.6",
    "com.lihaoyi" %%% "scalarx" % "0.2.8",
    "com.lihaoyi" %%% "upickle" % "0.2.8",
    "com.lihaoyi" %%% "autowire" % "0.2.5"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework"))
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.8.0",
      "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
      "com.timushev" %%% "scalatags-rx" % "0.1.0"
    ),
    crossTarget in(Compile, fullOptJS) := baseDirectory.value / "../jvm/src/main/public/js/",
    crossTarget in(Compile, fastOptJS) := baseDirectory.value / "../jvm/src/main/public/js/",
    crossTarget in(Compile, packageJSDependencies) := baseDirectory.value / "../jvm/src/main/public/js/",
    crossTarget in(Compile, packageScalaJSLauncher) := baseDirectory.value / "../jvm/src/main/public/js/",
    artifactPath in(Compile, fastOptJS) := ((crossTarget in(Compile, fastOptJS)).value / ((moduleName in fastOptJS).value + "-opt.js"))
  )
  .jvmSettings(
    libraryDependencies ++= {
      val akkaV = "2.3.9"
      val sprayV = "1.3.3"
      Seq(
        "io.spray" %% "spray-can" % sprayV,
        "io.spray" %% "spray-routing" % sprayV,
        "io.spray" %% "spray-testkit" % sprayV % "test",
        "io.spray" %% "spray-json" % "1.3.1",
        "com.typesafe.akka" %% "akka-actor" % akkaV,
        "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
        "org.scalatest" %% "scalatest" % "2.2.2" % "test",
        "org.scalamock" %% "scalamock-scalatest-support" % "3.1.4" % "test",
        "org.scala-js" %% "scalajs-stubs" % "0.6.2" % "provided"
      )
    }
  )
  .jvmSettings(Revolver.settings: _*)

lazy val js = theapp.js

lazy val jvm = theapp.jvm
