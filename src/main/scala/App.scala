package playground
//input handelling deps

import scala.collection.mutable.Stack
import java.io.{InputStream}

// ? async deps
import scala.concurrent.Await
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

//global deps
import Globals.{dataWorker, solverWorker}

object App {


  implicit val timeout = Timeout(1 seconds)

  /**
    * @param in An InputStream
    *
    * @return   Either[String, Long] 
    * */
  def run(in: InputStream): Either[String, Long] = {
    try {
      val datafuture = dataWorker ? UserInput(in)
      val stack = Await
        .result(datafuture, timeout.duration)
        .asInstanceOf[Stack[(Long, Long)]]

      val solutionfuture = solverWorker ? ProcessedData(stack)
      val res =
        Await.result(solutionfuture, timeout.duration).asInstanceOf[Long]
      Right(res / (stack.size))
    } catch {
      case e: Throwable => Left(e.getMessage)
    }
  } //.. end process
} //.. end OrderProcessor
