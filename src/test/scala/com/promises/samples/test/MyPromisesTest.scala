package com.promises.samples.test

import com.promises.samples.MyPromises._
import com.util.{FactorNumber, SumSequence}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.{Future, Promise}


/**
  * Created by raitis on 04/08/2016.
  */

class MyPromisesTest extends FlatSpec with Matchers with ScalaFutures  {


  "computeSquare 2" should "return Future of 4" in {
    val expectedReturn = 4
    val actualReturn: Promise[Int] = computeSquare(2)

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

  "computeSquare Future 2" should "return Future of 4" in {
    val expectedReturn = 4
    val actualReturn: Promise[Int] = computeSquare(Future(2))

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

  "find Max Factorial of 256" should "return Future(128)" in {
    val expectedReturn = 128
    val actualReturn: Promise[Long] = findMaxFactor(FactorNumber(256))

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

  "find Future Max Factorial of 128" should "return Future(64)" in {
    val expectedReturn = 64
    val actualReturn: Promise[Long] = findMaxFactor(Future(FactorNumber(128)))

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

  "compute Risky Sum Fallback On Safe Sum for 10 risk and 15 safe" should "should return Future(1)" in {
    val expectedReturn = 5
    val actualReturn: Promise[Int] = computeRiskySumFallbackOnSafeSum(SumSequence(1,4),SumSequence(1,5))

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

  "sum of all max factor numbers of 4,8,16" should "should return Future(14)" in {
    val expectedReturn = 14
    val seq = Seq(FactorNumber(4), FactorNumber(8), FactorNumber(16))
    val actualReturn: Promise[Long] = findSumOfAllMaxFactors(seq)

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

  "sum of all max factor numbers of 4,8,16 in paralel" should "should return Future(14)" in {
    val expectedReturn = 28
    val seq = Seq(FactorNumber(8), FactorNumber(16), FactorNumber(32))
    val actualReturn: Promise[Long] = findMaxFactorOfAllMaxFactorsInParallel(seq)

    whenReady(actualReturn.future) { result =>
      assert(result === expectedReturn  )
    }
  }

}
