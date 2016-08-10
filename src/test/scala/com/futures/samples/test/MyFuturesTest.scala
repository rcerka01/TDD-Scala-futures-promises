package com.futures.samples.test

import com.futures.samples.MyFutures._
import com.util.{SumSequence, FactorNumber}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by raitis on 04/08/2016.
  */

class MyFuturesTest extends FlatSpec with Matchers with ScalaFutures  {


  "computeSquare 2" should "return Future of 4" in {
    val expectedReturn = 4
    val actualReturn: Future[Int] = computeSquare(2)

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }


  "computeSquare Future 2" should "return Future of 4" in {
    val expectedReturn = 4
    val actualReturn: Future[Int] = computeSquare(Future(2))

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }


  "find Max Factorial of 256" should "return Future(128)" in {
    val expectedReturn = 128
    val actualReturn: Future[Long] = findMaxFactor(FactorNumber(256))

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }


  "find Future Max Factorial of 128" should "return Future(64)" in {
    val expectedReturn = 64
    val actualReturn: Future[Long] = findMaxFactor(Future(FactorNumber(128)))

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }

  "compute Risky Sum Fallback On Safe Sum for 10 risk and 15 safe" should "should return Future(1)" in {
    val expectedReturn = 5
    val actualReturn: Future[Int] = computeRiskySumFallbackOnSafeSum(SumSequence(1,4),SumSequence(1,5))

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }

  "sum of all max factor numbers of 4,8,16" should "should return Future(14)" in {
    val expectedReturn = 14
    val seq = Seq(FactorNumber(4), FactorNumber(8), FactorNumber(16))
    val actualReturn: Future[Long] = findSumOfAllMaxFactors(seq)

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }


  "sum of all max factor numbers of 4,8,16 in paralel" should "should return Future(14)" in {
    val expectedReturn = 28
    val seq = Seq(FactorNumber(8), FactorNumber(16), FactorNumber(32))
    val actualReturn: Future[Long] = findMaxFactorOfAllMaxFactorsInParallel(seq)

    whenReady(actualReturn) { result =>
      assert(result === expectedReturn  )
    }
  }

}
