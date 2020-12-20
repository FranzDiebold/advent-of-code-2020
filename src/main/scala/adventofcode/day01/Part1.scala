/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/1
 * Day 1: Report Repair
 * Part 1
 */

package day01

import scala.io.Source

object Part1 {
  def getSpecialNumberPair(fileName: String, targetValue: Int): Option[Option[Tuple2[Int, Int]]] = {
    Source
      .fromResource(fileName)
      .getLines()
      .map(_.toInt)
      .scanLeft[Tuple2[Option[Int], Set[Int]]]((None, Set[Int]()))({
        case ((Some(previousValue), allPreviousValues), currentValue) =>
          (Option(currentValue), allPreviousValues + previousValue)
        case ((None, allPreviousValues), currentValue) => (Option(currentValue), allPreviousValues)
      })
      .find({
        case (Some(currentValue), allPreviousValues) =>
          allPreviousValues.contains(targetValue - currentValue)
        case (None, allPreviousValues) => false
      })
      .map({
        case (Some(currentValue), _) => Some(((targetValue - currentValue), currentValue))
        case (None, _)               => None
      })
  }

  def main(args: Array[String]): Unit = {
    val targetValue = 2020
    val fileName = "day01.txt"
    val pair: Option[Option[Tuple2[Int, Int]]] = getSpecialNumberPair(fileName, targetValue)

    pair match {
      case Some(Some(pairValue)) =>
        println(
          s"The two entries that sum to $targetValue are $pairValue. Their product is ${pairValue._1 * pairValue._2}."
        )
      case _ => println(s"No two entries can be found that sum up to $targetValue!")
    }
  }
}
