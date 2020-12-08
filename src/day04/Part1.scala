/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/4
 * Day 4: Passport Processing
 * Part 1
 */

package day04

import scala.io.Source
import scala.util.matching.Regex

object Part1 {
  def splitIterator[T](iter: Iterator[T], separator: T) = new Iterator[Seq[T]] {
    def hasNext = iter.hasNext
    def next() = iter.takeWhile(_ != separator).toSeq
  }

  def readPassports(fileName: String): Iterator[Map[String, String]] = {
    val fileLines: Iterator[String] = Source
      .fromFile(fileName)
      .getLines()

    val keyValueRegex: Regex = "(.*):(.*)".r

    splitIterator(fileLines, "")
      .map((lines: Seq[String]) => lines
        .map((line: String) => {
          line.split(" ")
          .foldLeft(Map[String, String]())((partialPassport: Map[String, String], rawElement: String) => {
            rawElement match {
              case keyValueRegex(key: String, value: String) => partialPassport + (key -> value)
              case _ => partialPassport
            }
          })
        })
        .reduce(_ ++ _)
      )
  }

  def isPasswortValid(passport: Map[String, String]): Boolean = {
    val requiredPassportFields: Set[String] = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    requiredPassportFields subsetOf passport.keySet
  }

  def getNumberOfValidPassports(fileName: String): Int = {
    readPassports(fileName).count(isPasswortValid)
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val numValidPassports = getNumberOfValidPassports(fileName)
    println(s"The number of valid passports is ${numValidPassports}.")
  }
}
