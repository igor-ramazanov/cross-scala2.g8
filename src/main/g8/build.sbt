import scala.scalanative.build._

ThisBuild / organization := "$package$"
ThisBuild / scalaVersion := "$scalaVersion$"

lazy val root = (project in file(".")).aggregate($name;format="camel"$.js, $name;format="camel"$.jvm, $name;format="camel"$.native)

val VersionZio = "1.0.13"
val VersionZioConfig = "2.0.4"
val VersionZioCache = "0.1.1"

lazy val $name;format="camel"$ = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .in(file("."))
  .settings(
    name := "$name$",
    scalacOptions ++= List(
      "-Yno-predef",
    ),
    libraryDependencies ++= List(
      "dev.zio" %%% "zio" % VersionZio,
      "dev.zio" %%% "zio-streams" % VersionZio,
      "dev.zio" %%% "zio-stacktracer" % VersionZio,
      "dev.zio" %%% "zio-concurrent" % VersionZio,
      "dev.zio" %%% "zio-test" % VersionZio,
      "dev.zio" %%% "zio-test-scalacheck" % VersionZio,
      "dev.zio" %%% "zio-test-sbt" % VersionZio,
      "dev.zio" %%% "zio-config" % VersionZioConfig,
      "dev.zio" %%% "zio-cache" % VersionZioCache,
      "io.github.cquiroz" %%% "scala-java-time" % "2.4.0-M2"
    )
  )
  .jvmSettings(
    fork := true,
    trapExit := false,
    outputStrategy := Some(OutputStrategy.StdoutOutput),
    Global / cancelable := true,
    run / connectInput := true
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true
  )
  .nativeSettings(
    nativeConfig ~= (
      _.withLTO(LTO.none)
        .withMode(Mode.debug)
        .withGC(GC.none)
    )
  )
