package playground

import akka.actor.{ActorSystem, Props}
object Globals { 

  val system = ActorSystem("highlander")
  val dataWorker = system.actorOf(Props[Preprocessor])
  val solverWorker = system.actorOf(Props[Processor])

  var debug = false
  def turnDebugOn = debug = true
  def turnDebugOff= debug = false
  def getDebugFlag = debug

}
