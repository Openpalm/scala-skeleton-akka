package playground

import akka.actor.{ActorSystem, Props}


object Globals { 

  private var debug = false
  def getDebugFlag = debug
  def toggleDebug = debug = !debug

  val system = ActorSystem("highlander")
  val worker1 = system.actorOf(Props[Ping])
  val worker2 = system.actorOf(Props[Pong])

  case class Message(string: String)

}
