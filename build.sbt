name := "akka-throttler"
 
organization := "akka.pattern.throttle"

version := "1.0-SNAPSHOT"
 
scalaVersion := "2.9.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
 
crossScalaVersions := Seq("2.9.2", "2.9.1")
 
libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor"        % "2.0.3" withSources,
  "com.typesafe.akka" % "akka-testkit"      % "2.0.3" % "test" withSources,
  "junit"             % "junit"             % "4.5"   % "test" withSources,
  "org.scalatest"     %% "scalatest"        % "1.6.1" % "test" withSources,
  "com.ning"          % "async-http-client" % "1.7.4" withSources
)

// This assumes that you have checked out the Github gh-pages branch as a directory 'gh-pages'
// next to the akka-throttler project directory:
publishTo := Some(Resolver.file("file",  new File("../gh-pages/maven-repo")))
