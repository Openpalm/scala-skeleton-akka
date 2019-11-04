package playground

// async deps
import scala.concurrent.Await
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

//global deps
import Globals.{worker1, worker2}

// try 
import scala.util.{Try}

object App {

  implicit val timeout = Timeout(1 seconds)

  def run: Try[Unit] = {
    Try {
      val ping = worker1 ? "hello?"
      val data = Await.result(ping, timeout.duration) .asInstanceOf[String]
      val pong = worker2 ? "this kills the crab."
      val res = Await.result(pong, timeout.duration).asInstanceOf[String]
    }
  }
}
