/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/10
 * Day 10: Adapter Array
 * Part 2
 */

package day10

import day10.Part1.{readJoltageRatings}

object Part2 {
  def splitIterator[T](iter: Iterator[T], separator: T) = new Iterator[Seq[T]] {
    def hasNext = iter.hasNext
    def next() = iter.takeWhile(_ != separator).toSeq
  }

  def tribonacci(n: Int): Int = {
    n match {
      case 0 => 1
      case 1 => 1
      case 2 => 2
      case n: Int => tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3)
    }
  }

  def getDifferencesProduct(fileName: String): Long = {
    val joltageRatings: Iterator[Int] = (readJoltageRatings(fileName) ++ Iterator(0))
      .toSeq
      .sorted
      .sliding(2)
      .map((pair: Seq[Int]) => pair(1) - pair(0))
      .takeWhile(_ <= 3)

    splitIterator(joltageRatings, 3)
      .map(_.size)
      .filter(_ > 0)
      .map(tribonacci(_).toLong)
      .reduce(_ * _)
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val numberOfDistinctWays: Long = getDifferencesProduct(fileName)
    println(s"The total number of distinct ways is ${numberOfDistinctWays}.")
  }
}
