package playground

// async deps
//import scala.concurrent.Await
//import akka.pattern.ask

//global deps
import Globals.{worker1, worker2, Message}

// try 
import scala.util.Try

object App {

  def run: Try[Unit] = {
    Try {
      val ping = worker1 ! "hello?"
      val pong = worker2 ! "this kills the crab."
    }
  }
}
