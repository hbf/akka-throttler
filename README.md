A throttler for Akka 2.0
========================

What is this about?
--------------------

A typical usage scenario for a throttler is this: your application needs to make calls to an external webservice and this webservice has a restriction in place. You may only make _X_ calls in _Y_ seconds. You get blocked or need to pay gold if you don't stay under this limit. With a throttler, you can ensure that the calls you make do not cross the threshold rate.

You create a throttler by specifying a _rate_, for example `3 msgsPer (1 second)` and a _target actor_. You can then send the throttler messages at any rate, and the throttler will send the messages to the target at a rate at most 3 msg/s.

Example
-------

```scala
// A simple actor that prints whatever it receives
val printer = system.actorOf(Props(new Actor {
  def receive = {
    case x => println(x)
  }
}))

// The throttler for this example, setting the rate
val throttler = system.actorOf(Props(new TimerBasedThrottler(3 msgsPer (1 second))))

// Set the target
throttler ! SetTarget(Some(printer))

// These three messages will be sent to the printer immediately
throttler ! Queue("1")
throttler ! Queue("2")
throttler ! Queue("3")

// These two will wait at least until 1 second has passed
throttler ! Queue("4")
throttler ! Queue("5")

```

Status
------
Take a look at the [API][3] to see what is provided. Most of the functionality is documented [here][7] and "described" in the [tests][6]. To run them, check out the code and do a `sbt test`. (You will need at least sbt version 0.11.3.)

Currently, the project only provides a timer-based implementation of a throttler, see [TimerBasedThrottler][2]. As [described][2] in the class documentation, this throttler only provides weak guarantees.

There are plans to add an implementation of a history-based throttler that provides stronger guarantees, like for example the [one][4] by Charles Cordingley.

Download, Maven, SBT
--------------------
The packages are published to the Maven repository [http://hbf.github.com/akka-throttler/maven-repo][8]. You get the latest version using 

```scala
    resolvers += "akka-throttler-github-repository" at "http://hbf.github.com/akka-throttler/maven-repo"
    
    libraryDependencies ++= Seq(
      // ...
      "akka-throttler" %% "com.dreizak.akka" % "1.0-SNAPSHOT" withSources
    )
```

The Maven repository contains builds for Scala 2.9.1 and 2.9.2. If you need something else, open an issue to let me know.

For Maven, add the following to your `pom.xml`:

```xml
	<repositories>
		<!-- ... -->
		<repository>
			<id>akka-throttler-github-repository</id>
			<url>http://hbf.github.com/akka-throttler/maven-repo</url>
		</repository>
	</repositories>
	<dependencies>
		<!-- ... -->
		<dependency>
			<groupId>akka-throttler</groupId>
			<artifactId>com.dreizak.akka_SCALAVERSION</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
	</dependencies>
```

Here, `SCALAVERSION` should be `2.9.1` or `2.9.2`.


License
-------
All of the code in this project is available under the [Creative Commons – Attribution 3.0 Unported (CC BY 3.0)][5] license.

  [1]: http://akka.io/
  [2]: http://hbf.github.com/akka-throttler/doc/api/#akka.util.throttle.TimerBasedThrottler
  [3]: http://hbf.github.com/akka-throttler/doc/api/
  [4]: http://www.cordinc.com/blog/2010/04/java-multichannel-asynchronous.html
  [5]: http://creativecommons.org/licenses/by/3.0/
  [6]: https://github.com/hbf/akka-throttler/blob/master/src/test/scala/akka/util/throttle/TimerBasedThrottlerSpec.scala
  [7]: http://hbf.github.com/akka-throttler/doc/api/#akka.util.throttle.Throttler
  [8]: http://hbf.github.com/akka-throttler/maven-repo