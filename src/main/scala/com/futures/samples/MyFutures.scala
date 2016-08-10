package com.futures.samples

/**
  * Created by raitis on 04/08/2016.
  */

import com.util.{SumSequence, FactorNumber}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object MyFutures {

  def computeSquare(n: Int): Future[Int] = {
     Future {
       n * n
     }
  }

  def computeSquare(f: Future[Int]): Future[Int] = {
    f.map(value => value * value)
  }

  def findMaxFactor(work: FactorNumber): Future[Long] = {
    Future {
      work.perform.max
    }
  }

  def findMaxFactor(work: Future[FactorNumber]): Future[Long] = {
    work.map(_.perform.max)
  }

  def computeRiskySumFallbackOnSafeSum(riskyWork: SumSequence, safeWork: SumSequence): Future[Int] = {
    Future {
      safeWork.perform - riskyWork.perform
    }
  }

  def findSumOfAllMaxFactors(work: Seq[FactorNumber]): Future[Long] = {
    Future {
      work.map(number => number.perform.max).sum
    }
  }

  def findMaxFactorOfAllMaxFactorsInParallel(work: Seq[FactorNumber]): Future[Long] = {
    Future {
      work.par.map(number => number.perform.max).par.sum
    }
  }

}
