/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/14
 * Day 14: Docking Data
 * Part 1
 */

package day14

import scala.io.Source
import scala.util.matching.Regex

object Part1 {
  def readProgram(fileName: String): Iterator[Tuple2[String, Tuple2[BigInt, BigInt]]] = {
    val bitmaskRegex: Regex = """mask = ([01X]{36})""".r
    val writeValueRegex: Regex = """mem\[(\d*)\] = (\d*)""".r

    Source
      .fromFile(fileName)
      .getLines()
      .scanLeft[Tuple2[String, Tuple2[BigInt, BigInt]]](("", (-1, -1)))({
        case ((previousBitmask: String, _), line: String) => line match {
          case bitmaskRegex(bitmask: String) => (bitmask, (-1, -1))
          case writeValueRegex(memoryAddress: String, value: String) => (previousBitmask, (BigInt(memoryAddress), BigInt(value)))
          case _ => (previousBitmask, (-1, -1))
        }
      })
      .filter(_._2._1 != -1)
  }

  def bigIntToFixedLengthBinaryString(number: BigInt, length: Int = 36): String = number.toString(2).reverse.padTo(length, '0').reverse

  def binaryStringToBigInt(numberStr: String): BigInt = BigInt(numberStr, 2)

  def applyBitmask(bitmask: String, value: BigInt): BigInt = {
    val binaryValueString: String = (bitmask zip bigIntToFixedLengthBinaryString(value))
      .map({
        case (maskChar: Char, valueChar: Char) => maskChar match {
          case '0' | '1' => maskChar
          case 'X' => valueChar
        }
      })
      .mkString
    binaryStringToBigInt(binaryValueString)
  }

  def runInitializationProgram(program: Iterator[Tuple2[String, Tuple2[BigInt, BigInt]]]): Map[BigInt, BigInt] = {
    program
      .foldLeft[Map[BigInt, BigInt]](Map[BigInt, BigInt]()) {
        case (memory: Map[BigInt, BigInt], (bitmask: String, (memoryAddress: BigInt, value: BigInt))) => memory + (memoryAddress -> applyBitmask(bitmask, value))
      }
  }

  def sumMemoryValues(memory: Map[BigInt, BigInt]): BigInt = {
    memory
      .values
      .sum
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val program: Iterator[Tuple2[String, Tuple2[BigInt, BigInt]]] = readProgram(fileName)
    val initializedMemory: Map[BigInt, BigInt] = runInitializationProgram(program)
    val memoryValuesSum: BigInt = sumMemoryValues(initializedMemory)
    println(s"The sum of all values left in memory is ${memoryValuesSum}.")
  }
}
