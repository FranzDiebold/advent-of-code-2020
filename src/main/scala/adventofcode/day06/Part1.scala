/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/6
 * Day 6: Custom Customs
 * Part 1
 */

package day06

import scala.io.Source

object Part1 {
  def splitIterator[T](iter: Iterator[T], separator: T) = new Iterator[Seq[T]] {
    def hasNext = iter.hasNext
    def next() = iter.takeWhile(_ != separator).toSeq
  }

  def getNumberOfQuestionsAnyone(fileName: String): Int = {
    val fileLines: Iterator[String] = Source
      .fromResource(fileName)
      .getLines()

    splitIterator(fileLines, "")
      .map((lines: Seq[String]) => lines
        .map(_.toSet)
        .reduce(_ ++ _)
      )
      .map(_.size)
      .sum
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day06.txt"
    val numberOfQuestions: Int = getNumberOfQuestionsAnyone(fileName)
    println(s"The number of questions anyone answered with 'yes' is ${numberOfQuestions}.")
  }
}
