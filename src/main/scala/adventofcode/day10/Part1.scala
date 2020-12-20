/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/10
 * Day 10: Adapter Array
 * Part 1
 */

package day10

import scala.io.Source

object Part1 {
  def readJoltageRatings(fileName: String): Iterator[Int] = {
    Source
      .fromResource(fileName)
      .getLines()
      .map(_.toInt)
  }

  def getHistogram[T](values: Seq[T]): Map[T, Int] = {
    values
      .groupBy(x => x)
      .mapValues(_.length)
      .toMap
  }

  def getDifferencesProduct(fileName: String, maxJoltDifference: Int = 3): Int = {
    val joltageRatings: Seq[Int] = (readJoltageRatings(fileName) ++ Iterator(0))
      .toSeq
      .sorted
      .sliding(2)
      .map((pair: Seq[Int]) => pair(1) - pair(0))
      .takeWhile(_ <= maxJoltDifference)
      .toSeq :+ maxJoltDifference
    val differencesHistogram: Map[Int, Int] = getHistogram(joltageRatings)

    differencesHistogram(1) * differencesHistogram(3)
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day10.txt"
    val differencesProduct: Int = getDifferencesProduct(fileName)
    println(s"The number of 1-jolt differences multiplied by the number of 3-jolt differences is ${differencesProduct}.")
  }
}
