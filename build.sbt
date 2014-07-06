import sbt._
import Keys._

name := "activator-mirror"

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= {
  val akkaVersion  = "2.3.4"
  val sprayVersion = "1.3.1"
  Seq(
    "com.typesafe.akka"     %% "akka-actor"                  % akkaVersion,
    "com.typesafe.akka"     %% "akka-slf4j"                  % akkaVersion exclude ("org.slf4j", "slf4j-api"),
    "io.spray"              %% "spray-can"                   % sprayVersion,
    "io.spray"              %% "spray-routing"               % sprayVersion,
    "com.google.apis"        % "google-api-services-mirror"  % "v1-rev51-1.18.0-rc",
    "com.google.http-client" % "google-http-client-jackson2" % "1.18.0-rc",
    "io.spray"              %% "spray-testkit"               % sprayVersion % "test",
    "com.typesafe.akka"     %% "akka-testkit"                % akkaVersion % "test"
  )
}

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

unmanagedResourceDirectories in Compile <++= baseDirectory {
  base => Seq(base / "src/main/angular")
}
