package playground

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{FunSuite, MustMatchers}

import org.scalatest._
import Matchers._

class theTests extends FunSuite with MustMatchers with TypeCheckedTripleEquals {


  sealed trait TestData[A,B] {
    def data: Either[A, B]
  }
  case class GoodData[A,B](data: Either[A,B]) extends TestData[A,B]
  case class BadData[A,B](data: Either[A,B]) extends TestData[A,B]


  test("a simple test finds good data") {
    val testData = GoodData(Right(1))
    testData.data must === (Right(1))
  }

  test("a simple test finds bad data") {
    val testData = BadData(Left(0))
    testData.data must === (Left(0))
  }


}
