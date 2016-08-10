package com.promises.samples

import com.util.{SumSequence, FactorNumber}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

/**
  * Created by raitis on 05/08/2016.
  */
object MyPromises extends App {

  def computeSquare(n: Int): Promise[Int] = {
    val p = Promise[Int]()
    p completeWith Future {
      n * n
    }
  }

  def computeSquare(f: Future[Int]): Promise[Int] = {
    val p = Promise[Int]()
    p completeWith f.map(value => value * value)
  }

  def findMaxFactor(work: FactorNumber): Promise[Long] = {
    val p = Promise[Long]
    p completeWith Future {
      work.perform.max
    }
  }

  def findMaxFactor(work: Future[FactorNumber]): Promise[Long] = {
    val p = Promise[Long]
    p completeWith work.map(_.perform.max)
  }

  def computeRiskySumFallbackOnSafeSum(riskyWork: SumSequence, safeWork: SumSequence): Promise[Int] = {
    val p = Promise[Int]
    p completeWith Future {
      safeWork.perform - riskyWork.perform
    }
  }

  def findSumOfAllMaxFactors(work: Seq[FactorNumber]): Promise[Long] = {
    val p = Promise[Long]
    p completeWith Future {
      work.map(number => number.perform.max).sum
    }
  }

  def findMaxFactorOfAllMaxFactorsInParallel(work: Seq[FactorNumber]): Promise[Long] = {
    val p = Promise[Long]
    p completeWith  Future {
      work.par.map(number => number.perform.max).par.sum
    }
  }

}