import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val buildVersion =  "1.5.1"

  lazy val root = Project(id = "play-plugins-salat", base = file("."), settings = Project.defaultSettings ++ Publish.settings).settings(
    organization := "se.radley",
    description := "MongoDB Salat plugin for PlayFramework 2",
    version := buildVersion,
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    crossScalaVersions ++= Seq("2.10.4"),
    parallelExecution in Test := false,
    testFrameworks += TestFrameworks.Specs2,

    resolvers ++= Seq(
      "play Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/",
      "scalaz-bintray"  at "http://dl.bintray.com/scalaz/releases",
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    ),

    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % "2.4.3" % "provided",
      "com.typesafe.play" % "play-exceptions" % "2.4.3" % "provided",
      "com.typesafe.play" %% "play-specs2" % "2.4.3" % "test",
      "com.novus" %% "salat" % "1.9.8",
      "org.mongodb" %% "casbah-gridfs" % "2.7.2"
    )
    
  )
}

object Publish {
  lazy val settings = Seq(
    publishMavenStyle := true,
    publishTo := Some(Resolver.file("se.radley", file(""))(Patterns(true, Resolver.mavenStyleBasePattern))),
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/leon/play-salat")),
    pomExtra := (
      <scm>
        <url>git://github.com/leon/play-salat.git</url>
        <connection>scm:git://github.com/leon/play-salat.git</connection>
      </scm>
      <developers>
        <developer>
          <id>leon</id>
          <name>Leon Radley</name>
          <url>http://github.com/leon</url>
        </developer>
      </developers>)
  )
}
