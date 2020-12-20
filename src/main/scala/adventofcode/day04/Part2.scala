/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/4
 * Day 4: Passport Processing
 * Part 2
 */

package day04

import scala.util.matching.Regex

import day04.Part1.{readPassports}

object Part2 {
  def isPasswortValid(passport: Map[String, String]): Boolean = {
    val yearRegex: Regex = """(\d{4})""".r
    val yearsValid: Boolean = Seq(
      ("byr", 1920, 2002),
      ("iyr", 2010, 2020),
      ("eyr", 2020, 2030)
    )
      .map({ case (key: String, min: Int, max: Int) =>
        passport.get(key) match {
          case Some(yearRegex(year: String)) => min <= year.toInt && year.toInt <= max
          case _                             => false
        }
      })
      .reduce(_ && _)

    val hgtCmRegex: Regex = """(\d{3})cm""".r
    val hgtInRegex: Regex = """(\d{2})in""".r
    val hgtValid: Boolean = passport.get("hgt") match {
      case Some(hgtCmRegex(hgt: String)) => 150 <= hgt.toInt && hgt.toInt <= 193
      case Some(hgtInRegex(hgt: String)) => 59 <= hgt.toInt && hgt.toInt <= 76
      case _                             => false
    }

    val hclRegex: Regex = "#[0-9a-f]{6}".r
    val hclValid: Boolean = passport.get("hcl") match {
      case Some(hclRegex()) => true
      case _                => false
    }

    val eclValid: Boolean = passport.get("ecl") match {
      case Some("amb") | Some("blu") | Some("brn") | Some("gry") | Some("grn") | Some("hzl") |
          Some("oth") =>
        true
      case _ => false
    }

    val pidRegex: Regex = """\d{9}""".r
    val pidValid: Boolean = passport.get("pid") match {
      case Some(pidRegex()) => true
      case _                => false
    }

    yearsValid && hgtValid && hclValid && eclValid && pidValid
  }

  def getNumberOfValidPassports(fileName: String): Int =
    readPassports(fileName).count(isPasswortValid)

  def main(args: Array[String]): Unit = {
    val fileName = "day04.txt"
    val numValidPassports = getNumberOfValidPassports(fileName)
    println(s"The number of valid passports (with value validation) is ${numValidPassports}.")
  }
}
