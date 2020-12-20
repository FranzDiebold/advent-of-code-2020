/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/2
 * Day 2: Password Philosophy
 * Part 2
 */

package day02

import scala.io.Source

object Part2 {
  def isPasswordValid(password: String, char: Char, first: Int, second: Int): Boolean =
    password(first - 1) == char ^ password(second - 1) == char

  def extractValuesFromLine(line: String): Option[Tuple3[Tuple2[Int, Int], Char, String]] = {
    line match {
      case s"${first}-${second} ${char}: ${password}" =>
        Some(((first.toInt, second.toInt), char(0), password))
      case _ => None
    }
  }

  def getNumberOfValidPasswords(fileName: String): Int = {
    Source
      .fromResource(fileName)
      .getLines()
      .map(extractValuesFromLine)
      .count({
        case Some(((first: Int, second: Int), char: Char, password: String)) =>
          isPasswordValid(password, char, first, second)
        case None => false
      })
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day02.txt"
    val numValidPasswords = getNumberOfValidPasswords(fileName)
    println(s"The number of valid passwords (new interpretation) is ${numValidPasswords}.")
  }
}
