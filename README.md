A throttler for Akka 2.0
========================

What is this about?
--------------------

Create a throttler by specifying a _rate_, for example `3 msgsPer (1 second)` (i.e., 3 messages per second) and a _target actor_. You can then send the throttler messages at any rate, and the throttler will send the messages to the target at a rate at most 3 msg/s.

How can I use it?
-----------------

```scala
// A simple actor that prints whatever it receives
val printer = system.actorOf(Props(new Actor {
  def receive = {
    case x => println(x)
  }
}))

// The throttler for this example
val throttler = system.actorOf(Props[TimerBasedThrottler])

// Set the target and rate
throttler ! SetTarget(Some(printer))
throttler ! SetRate(3 msgsPer (1 second))

// These three messages will be sent to the printer immediately
throttler ! Queue("1")
throttler ! Queue("2")
throttler ! Queue("3")

// These two will wait at least until 1 second has passed
throttler ! Queue("4")
throttler ! Queue("5")
```

What is implemented?
--------------------
Currently, the project only provides a timer-based implementation for a throttler, see [TimerBasedThrottler][2].

Take a look at the [API][3].	

  [1]: http://akka.io/
  [2]: http://hbf.github.com/akka-throttler/doc/api/#akka.util.throttle.TimerBasedThrottler
  [3]: http://hbf.github.com/akka-throttler/doc/api/