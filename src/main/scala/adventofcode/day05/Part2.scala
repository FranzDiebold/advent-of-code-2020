/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/5
 * Day 5: Binary Boarding
 * Part 2
 */

package day05

import scala.io.Source

import day05.Part1.{readBoardingPass}

object Part2 {
  def findSeat(fileName: String): Option[Int] = {
    val seatPair: Option[Seq[Int]] = Source
      .fromResource(fileName)
      .getLines()
      .map(readBoardingPass(_))
      .toSeq
      .sorted
      .sliding(2)
      .find((neighbours: Seq[Int]) => neighbours(1) - neighbours(0) == 2)

    seatPair match {
      case Some(neighbours: Seq[Int]) => Some(neighbours(1) - 1)
      case None                       => None
    }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day05.txt"
    val seatIdOption: Option[Int] = findSeat(fileName)
    seatIdOption match {
      case Some(seatId) => println(s"The seat ID is ${seatId}.")
      case _            => println("Not seat ID found.")
    }
  }
}
