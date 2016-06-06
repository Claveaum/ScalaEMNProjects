package tweeter

import java.util.Observable

import scala.actors._

case class Tweet(content : String)
case class Receive(content : String)
case class Follow(user : String)
case class AddFollower(follower : Tweeter)
case class GetName()

class Tweeter(val user: String, private[this] val registry: Registry) extends Observable with Actor{
  registry ! Bind(user, this)
  private[this] var followers = List.empty[Tweeter]

  override def act(): Unit = {

    while(true) {
      receive {
        case Tweet(content) => {
          if (content != null){
            for (follower <- followers) {
              follower ! Receive(content)
            }
          }
        }
        case Receive(content : String) => {
          println(s"${sender.asInstanceOf[Tweeter].user} says : $content")
          setChanged()
          notifyObservers(new TweetView(sender.asInstanceOf[Tweeter], content))
        }
        case Follow(name : String) => {
          registry !? Lookup(name) match
          { case Some(tweeter:Tweeter) => tweeter ! AddFollower(this) ; println(s"$user follow $name")
          case None =>
          }
        }
        case AddFollower(follower : Tweeter) => {
          if (!followers.contains(follower)) { followers = follower :: followers ;  println(s"$user have a new follower named ${sender.asInstanceOf[Tweeter].user}") }
        }
      }
    }
  }
}
