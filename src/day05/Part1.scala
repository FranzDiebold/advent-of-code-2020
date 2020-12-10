/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/5
 * Day 5: Binary Boarding
 * Part 1
 */

package day05

import scala.io.Source

object Part1 {
  def readBoardingPass(bspCode: String): Int = {
    bspCode
      .map({
        case 'F' | 'L' => 0
        case 'B' | 'R' => 1
      })
      .foldLeft(0)((id: Int, currentDigit: Int) => 2 * id + currentDigit)
  }

  def getHighestSeatId(fileName: String): Int = {
    Source
      .fromFile(fileName)
      .getLines()
      .map(readBoardingPass(_))
      .max
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val highestSeatId: Int = getHighestSeatId(fileName)
    println(s"The highest seat ID is ${highestSeatId}.")
  }
}
