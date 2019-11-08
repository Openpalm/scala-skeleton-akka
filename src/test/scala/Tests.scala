package playground

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.charset.StandardCharsets

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{FunSuite, MustMatchers}

import org.scalatest._
import Matchers._

class AppTest
extends FunSuite
with MustMatchers
with TypeCheckedTripleEquals {

  import Globals.{
    input, 
    debug, 
    orderLimit,
    factoryLimit,
    getStream
  }

  test("process input ") {
    val res = App.run(getStream)
    if (debug) {
      println(res)
    }
    res must ===(Right(8L))
  }
  test("catches a left") {
    val res = App.run(getStream)
    if (debug) {
      println(res)
    }
    res.isLeft should be(true)
  }
  test("exceeds order limit") {
    val input =
      s"""${orderLimit + 1}
    |0 3
    |1 9
    |""".stripMargin
    val badData: InputStream =
      new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
    val res = App.run(badData)
    if (debug) {
      println(res)
    }
    res.isLeft should be(true)
  }
  test("exceeds factory limit") {
    val input =
      s"""3
    |${factoryLimit + 1} 3
    |1 9
    |""".stripMargin
    val badData: InputStream =
      new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
    val res = App.run(badData)
    if (debug) {
      println(res)
    }
    res.isLeft should be(true)
  }
}
