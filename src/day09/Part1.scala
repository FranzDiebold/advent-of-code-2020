/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/9
 * Day 9: Encoding Error
 * Part 1
 */

package day09

import scala.io.Source

object Part1 {
  def readInput(fileName: String): Iterator[Long] = {
    Source
      .fromFile(fileName)
      .getLines()
      .map(_.toLong)
  }

  def isPairSum(targetNumber: Long, numbers: Set[Long]): Boolean = {
    val complementNumbers: Set[Long] = numbers
      .map(targetNumber - _)
      .toSet

    !(complementNumbers & numbers).isEmpty
  }

  def findFirstNonPairSumNumber(input: Iterator[Long], preambleLength: Int): Option[Long] = {
    val firstNumberPair = input
      .sliding(preambleLength + 1)
      .map((window: Seq[Long]) => (window.slice(0, window.size - 1), window(window.size - 1)))
      .find({
          case (numbers: Seq[Long], targetNumber: Long) => !isPairSum(targetNumber, numbers.toSet)
      })

    firstNumberPair match {
      case Some((_, targetNumber: Long)) => Some(targetNumber)
      case None => None
    }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val preambleLength = 25
    val input: Iterator[Long] = readInput(fileName)
    findFirstNonPairSumNumber(input, preambleLength) match {
      case Some(programResult: Long) => println(s"The first number that does not have this property is ${programResult}.")
      case None => println("No number found with this property.")
    }
  }
}
