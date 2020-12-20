/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/2
 * Day 2: Password Philosophy
 * Part 1
 */

package day02

import scala.io.Source

object Part1 {
  def isPasswordValid(password: String, char: Char, min: Int, max: Int): Boolean = {
    val countChars = password.count(_ == char)
    min <= countChars && countChars <= max
  }

  def extractValuesFromLine(line: String): Option[Tuple3[Tuple2[Int, Int], Char, String]] = {
    line match {
      case s"${min}-${max} ${char}: ${password}" =>
        Some(((min.toInt, max.toInt), char(0), password))
      case _ => None
    }
  }

  def getNumberOfValidPasswords(fileName: String): Int = {
    Source
      .fromResource(fileName)
      .getLines()
      .map(extractValuesFromLine)
      .count({
        case Some(((min: Int, max: Int), char: Char, password: String)) =>
          isPasswordValid(password, char, min, max)
        case None => false
      })
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day02.txt"
    val numValidPasswords = getNumberOfValidPasswords(fileName)
    println(s"The number of valid passwords is ${numValidPasswords}.")
  }
}
