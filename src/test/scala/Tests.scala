package playground

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{FunSuite, MustMatchers}

import org.scalatest._ //has Matchers._
import Matchers._

class AppTest
extends FunSuite
with MustMatchers
with TypeCheckedTripleEquals {

  import Globals.{ 
    turnDebugOn,
    debug
  }
  import TestGlobals.{
   GOODORDER,
   BADORDER,
   BADORDERLIMIT,
   BADFACTORY
  }
  test("process input ") {
    val res = App.run(GOODORDER)
    if (debug) println(res)
    res must ===(Right(8L))
  }
  test("catch a left") {
    val res = App.run(BADORDER)
    if (debug) println(res)
    res.isLeft should be(true)
  }
  test("exceeds order limit") {
    val res = App.run(BADORDERLIMIT)
    if (debug) println(res)
    res.isLeft should be(true)
  }
  test("exceeds factory limit") {
    val res = App.run(BADFACTORY)
    if (debug) println(res)
    res.isLeft should be(true)
  }
}
