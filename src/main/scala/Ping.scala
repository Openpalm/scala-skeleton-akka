package playground

import akka.actor.{Actor}

class Ping extends Actor {
  override def receive: Receive = {
    case _ => context.stop(self)
  } 
}
