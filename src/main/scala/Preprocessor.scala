package playground

import scala.annotation.tailrec
import akka.actor.{Actor}
import scala.collection.mutable.Stack

import com.github.plokhotnyuk.jsoniter_scala.core._
import java.io.{InputStream}

case class UserInput(theStream: InputStream)

class Preprocessor extends Actor {
  val orderLimit = 100000
  val factoryLimit = 1000000000
  override def receive: Receive = {
    //case PizzaData(in) => OrderProcessor.process(in)
    case UserInput(in) => {
      try {
        sender ! extractStack(in) } catch { case e: Throwable => Left(e.getMessage)
      }
    }
  }

  /**
    * @param in InputStream
    * @return stack with items
    */
  def extractStack(in: InputStream): Stack[(Long, Long)] = {
    val outStack = Stack[(Long, Long)]()
    implicit val codec: JsonValueCodec[Long] = new JsonValueCodec[Long] {
      override def decodeValue(r: JsonReader, default: Long): Long = {
        val n = r.readInt
        if (n > orderLimit)
          throw new Exception("Order limit exceeded")
        for (i <- 1 to n) {
          val arrival = r.readLong()
          if (arrival > factoryLimit)
            throw new Exception("this pizza would take too long to bake.")
          val duration = r.readLong()
          if (duration > factoryLimit)
            throw new Exception("this pizza would take too long to bake.")
          outStack.push((arrival, duration))
        }
        default
      }
      override def encodeValue(x: Long, w: JsonWriter): Unit = ???
      override def nullValue: Long = 0L
    }
    readFromStream(in)
    outStack
  } //.. end extractStack
}
