/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/14
 * Day 14: Docking Data
 * Part 2
 */

package day14

import day14.Part1.{readProgram, bigIntToFixedLengthBinaryString, binaryStringToBigInt, sumMemoryValues}

object Part2 {
  def applyBitmask(bitmask: String, value: BigInt): String = {
    (bitmask zip bigIntToFixedLengthBinaryString(value))
      .map({
        case (maskChar: Char, valueChar: Char) => maskChar match {
          case '0' => valueChar
          case '1' | 'X' => maskChar
        }
      })
      .mkString
  }

  def getAllPossibleAddressesFromFloatingBits(addressWithFloatingBits: String): Seq[String] = {
    val numberOfFloatingBits: Int = addressWithFloatingBits.count(_ == 'X')
    (0 to (math.pow(2, numberOfFloatingBits).toInt - 1))
      .map(bigIntToFixedLengthBinaryString(_, numberOfFloatingBits))
      .map((floatingBitsValue: String) => floatingBitsValue
        .foldLeft[String](addressWithFloatingBits)((currentAddress: String, bitValue: Char) => currentAddress.replaceFirst("X", bitValue.toString))
      )
  }

  def runInitializationProgram(program: Iterator[Tuple2[String, Tuple2[BigInt, BigInt]]]): Map[BigInt, BigInt] = {
    program
      .foldLeft[Map[BigInt, BigInt]](Map[BigInt, BigInt]()) {
        case (memory: Map[BigInt, BigInt], (bitmask: String, (memoryAddress: BigInt, value: BigInt))) => {
          val memoryAddressWithAppliedBitmask: String = applyBitmask(bitmask, memoryAddress)
          getAllPossibleAddressesFromFloatingBits(memoryAddressWithAppliedBitmask)
            .foldLeft[Map[BigInt, BigInt]](memory) {
              case (currentMemory: Map[BigInt, BigInt], currentMemoryAddress: String) => currentMemory + (binaryStringToBigInt(currentMemoryAddress) -> value)
            }
        }
      }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day14.txt"
    val program: Iterator[Tuple2[String, Tuple2[BigInt, BigInt]]] = readProgram(fileName)
    val initializedMemory: Map[BigInt, BigInt] = runInitializationProgram(program)
    val memoryValuesSum: BigInt = sumMemoryValues(initializedMemory)
    println(s"The sum of all values left in memory (version 2) is ${memoryValuesSum}.")
  }
}
