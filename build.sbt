name := "akka-throttler"
 
version := "1.0"
 
scalaVersion := "2.9.1"
 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
 
libraryDependencies += "com.typesafe.akka" % "akka-actor"      % "2.0.2"

libraryDependencies += "com.typesafe.akka" % "akka-testkit"    % "2.0.2" % "test"

libraryDependencies += "junit"             % "junit"           % "4.5"   % "test"

libraryDependencies += "org.scalatest"     % "scalatest_2.9.1" % "1.6.1" % "test"
