/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/1
 * Day 1: Report Repair
 * Part 2
 */

package day01

import scala.io.Source

object Part2 {
  def getSpecialNumberTriple(fileName: String, targetValue: Int): Option[Option[Tuple3[Int, Int, Int]]] = {
    Source
      .fromResource(fileName)
      .getLines()
      .map(_.toInt)
      .scanLeft[Tuple3[Option[Int], Set[Int], Map[Int, Int]]]((None, Set[Int](), Map[Int, Int]()))({
        case ((Some(previousValue), allPreviousValues, allPreviousPairSumsMap), currentValue) => {
          (
            Option(currentValue),
            allPreviousValues + previousValue,
            allPreviousValues.foldLeft(allPreviousPairSumsMap) {
              (previousMap: Map[Int, Int], currentPrevValue: Int) => previousMap + ((previousValue + currentPrevValue) -> previousValue)
            }
          )
        }
        case ((None, allPreviousValues, allPreviousPairSumsMap), currentValue) =>
          (Option(currentValue), allPreviousValues, allPreviousPairSumsMap)
      })
      .find({
        case (Some(currentValue), _, allPreviousPairSumsMap) => allPreviousPairSumsMap contains (targetValue - currentValue)
        case (None, _, _) => false
      })
      .map({
        case (Some(currentValue), _, allPreviousPairSumsMap) =>
          Some((
            (targetValue - allPreviousPairSumsMap(targetValue - currentValue)) - currentValue,
            allPreviousPairSumsMap(targetValue - currentValue),
            currentValue
          ))
        case (None, _, _) => None
      })
  }

  def main(args: Array[String]): Unit = {
    val targetValue = 2020
    val fileName = "day01.txt"
    val triple: Option[Option[Tuple3[Int, Int, Int]]] = getSpecialNumberTriple(fileName, targetValue)

    triple match {
      case Some(Some(tripleValue)) =>
        println(s"The three entries that sum to $targetValue are $tripleValue. Their product is ${tripleValue._1 * tripleValue._2 * tripleValue._3}.")
      case _ => println(s"No three entries can be found that sum up to $targetValue!")
    }
  }
}
