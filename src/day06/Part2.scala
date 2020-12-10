/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/6
 * Day 6: Custom Customs
 * Part 2
 */

package day06

import scala.io.Source

import day06.Part1.{splitIterator}

object Part2 {
  def getNumberOfQuestionsEveryone(fileName: String): Int = {
    val fileLines: Iterator[String] = Source
      .fromFile(fileName)
      .getLines()

    splitIterator(fileLines, "")
      .map((lines: Seq[String]) => lines
        .map(_.toSet)
        .reduce(_ & _)
      )
      .map(_.size)
      .sum
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val numberOfQuestions: Int = getNumberOfQuestionsEveryone(fileName)
    println(s"The number of questions everyone answered with 'yes' is ${numberOfQuestions}.")
  }
}
