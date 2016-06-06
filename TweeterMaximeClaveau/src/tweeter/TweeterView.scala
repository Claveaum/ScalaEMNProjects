package tweeter
import java.awt.{BorderLayout, Container}
import java.awt.event.{ActionEvent, ActionListener}
import java.util.{Observable, Observer}
import javax.swing.{JButton, JFrame, JLabel, JTextArea}
/**
  * Created by mclaveau on 01/05/2016.
  */
case class TweetView(tweeter: Tweeter, content :String)
class TweeterView(tweeter : Tweeter) extends JFrame(tweeter.user) with Observer {

    private val label = new JLabel(tweeter.user)
    private val buttonRetweet = new JButton("Retweet")
    private val jTextArea = new JTextArea("")
    private [this] var lastTweetReceived:String = null

    buttonRetweet.addActionListener((_: ActionEvent) => tweeter ! Tweet(lastTweetReceived))

    override def update(o: Observable, arg: Any) { arg match { case TweetView(tweeterSender, contentSend) => jTextArea.append("\n"+tweeterSender.asInstanceOf[Tweeter].user+" says "+contentSend);lastTweetReceived=contentSend}}

    def addComponentsToPane {
      val pane: Container = getContentPane
      pane.add(label, BorderLayout.NORTH)
      pane.add(jTextArea, BorderLayout.CENTER)
      pane.add(buttonRetweet, BorderLayout.SOUTH)
    }

    implicit def handler2Listener(handler: ActionEvent => Unit): ActionListener = new ActionListener {
      override def actionPerformed(event: ActionEvent): Unit = handler(event)
    }
  }

  object TweeterView {
    private def createAndShowGUI(name: String, r:Registry) :Tweeter =  {
      val tweeter = new Tweeter(name,r)
      val frame: TweeterView = new TweeterView(tweeter)
      tweeter.addObserver(frame)
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
      frame.addComponentsToPane
      frame.pack
      frame.setVisible(true)
      tweeter.start()
      r ! Bind(tweeter.user, tweeter)
      tweeter
    }


    def main(args: Array[String]) {
      val r = new Registry
      r.start()
      val alice = createAndShowGUI("Alice",r)
      val bob = createAndShowGUI("Bob",r)
      val carol = createAndShowGUI("Carol",r)
      Thread.sleep(2000)
      bob ! Follow(alice.user)
      carol ! Follow(bob.user)
      Thread.sleep(1000)
      alice ! Tweet("I am Alice !")


    }
}
