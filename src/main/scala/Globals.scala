package playground

import akka.actor.{ActorSystem, Props}
import java.io.{ByteArrayInputStream, InputStream}
import java.nio.charset.StandardCharsets

object Globals { 
  val system = ActorSystem("highlander")
  val dataWorker = system.actorOf(Props[Preprocessor])
  val solverWorker = system.actorOf(Props[Processor])


  var debug = false
  def turnDebugOn = debug = true
  def turnDebugOff= debug = false
  def getDebugFlag = debug

  val orderLimit = 100000
  val factoryLimit = 1000000000
  val input =
      """3
    |0 3
    |1 9
    |2 5
    |""".stripMargin

  //small helper for testing.
  def getStream = {
    val bytes = input.getBytes(StandardCharsets.UTF_8)
    new ByteArrayInputStream(bytes)
  }



}
