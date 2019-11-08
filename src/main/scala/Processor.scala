package playground
import scala.annotation.tailrec
import akka.actor.{Actor}
import scala.collection.mutable.Stack

case class ProcessedData(stack: Stack[(Long, Long)])

class Processor extends Actor {
  val debug = Globals.getDebugFlag
  override def receive: Receive = {
    case ProcessedData(stack) => {
      try {
        //solve
        sender ! solve(
          stack.sortBy(_._1),
          workStack = Stack[(Long, Long)](),
          0,
          0
        )
      } catch {
        case e: Throwable => Left(e.getMessage)
      }
    }
  }

  /**
    * @param inStack stack with initial items
    * @param workStack empty stack (the item graph bzw. list) to hold jobs with duration > current time
    *									"the empty graph"
    * @param currentJobWaitTime the current job "projected" wait time
    * @param overallWaitTime accumulator, overall running wait time, the return.
    * @return overallWaitTIme or solve()
    */
  @tailrec private def solve(
      inStack: Stack[(Long, Long)],
      workStack: Stack[(Long, Long)],
      overallWaitTime: Long,
      currentJobWaitTime: Long
  ): Long = {
    if (debug) {
      println(s"""
          |inStack: $inStack
          |workStack: $workStack
          |overallWaitTime: $overallWaitTime
          |currentJobWaitTime: $currentJobWaitTime """.stripMargin)
    }
    if (inStack.isEmpty && workStack.isEmpty) overallWaitTime
    else {
      /* inStack.reverse.head._1 => last incoming's item arrival time/ordering */
      while (!inStack.isEmpty && inStack.reverse.head._1 <= currentJobWaitTime) {
        workStack.push(inStack.pop().swap)
      }
      if (!workStack.isEmpty) {
        val curtask = workStack.pop()
        solve(
          inStack,
          workStack,
          overallWaitTime + currentJobWaitTime + curtask._1 - curtask._2,
          currentJobWaitTime + curtask._1
        )
      } else {
        val element = inStack.pop().swap
        workStack.push(element)
        solve(inStack, workStack, overallWaitTime, workStack.head._2)
      }
    }
  } //end solve
}
