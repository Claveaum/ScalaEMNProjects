package tweeter

import scala.actors._

case class Bind(name: String, value: Any)
case class Lookup(name: String)

class Registry extends Actor {
  private var registry:Map[String, Any] = Map()
  def act() {
    //while(true) {
    loop {
      //receive {
      react {
        case Bind(name, value) => registry = registry + (name -> value)
        case Lookup(name) => reply(registry.get(name))
      }
    }
  }
}

object Test extends App {
  val r = new Registry
  r.start()
  val alice = new Tweeter("Alice",r)
  val bob = new Tweeter("Bob",r)
  alice.start()
  bob.start()
  r ! Bind("Alice", alice)
  r ! Bind("Bob", bob)
  alice ! Tweet("I am Alice !")
  Thread.sleep(1000)
  bob ! Follow("Alice")
  bob ! Follow("Alice")
  Thread.sleep(1000)
  alice ! Tweet(s"I am Alice !")
}
