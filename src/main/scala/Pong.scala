package playground

import akka.actor.{ Actor }

class Pong extends Actor {
  override def receive: Receive = {
    case _ => context.stop(self)
  }
}
