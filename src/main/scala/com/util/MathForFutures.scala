package com.util

/**
  * Created by raitis on 04/08/2016.
  */

abstract class Work {
  def perform(): Any
}

case class SumSequence(start: Int, stop: Int, delay: Long = 0) extends Work {
  lazy val perform: Int = {
    require(start >= 0)
    Thread.sleep(delay)
    (start to stop).sum
  }
}

case class FactorNumber(n: Long, delay: Long = 0) extends Work {
  lazy val perform: List[Long] = {
    Thread.sleep(delay)
    factor(n)
  }

  /**
    * Naive approach to number factorization
    */
  def factor(n: Long): List[Long] = {

    def factor0(a: Long, acc: List[Long]): List[Long] = {
      def isFactor(f: Long, p: Long): Boolean = p % f == 0
      if (a > 1) {
        if (isFactor(a, n))
          factor0(a - 1, List(a) ::: acc)
        else
          factor0(a - 1, acc)
      } else {
        acc
      }
    }
    factor0(n - 1, List())
  }
}
