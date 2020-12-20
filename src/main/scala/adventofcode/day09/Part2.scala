/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/9
 * Day 9: Encoding Error
 * Part 2
 */

package day09

import day09.Part1.{readInput, findFirstNonPairSumNumber}

object Part2 {
  def findContiguousNumbersIdxToSum(numbers: IndexedSeq[Long], targetNumber: Long, currentStart: Int = 0, currentEnd: Int = 0, currentSum: Long = 0): Option[Tuple2[Int, Int]] = {
    if (currentSum == targetNumber && (currentEnd - currentStart) > 1) {
      return Some((currentStart, currentEnd))
    }
    if (currentStart >= numbers.size - 1) {
      return None
    }
    if (currentSum > targetNumber || currentEnd >= numbers.size) {
      return findContiguousNumbersIdxToSum(numbers, targetNumber, currentStart + 1, currentStart + 1, 0)
    }
    return findContiguousNumbersIdxToSum(numbers, targetNumber, currentStart, currentEnd + 1, currentSum + numbers(currentEnd))
  }

  def findEncryptionWeakness(fileName: String, preambleLength: Int): Option[Long] = {
    val inputIndexedSeq: IndexedSeq[Long] = readInput(fileName).toIndexedSeq
    findFirstNonPairSumNumber(inputIndexedSeq.toIterator, preambleLength) match {
      case Some(firstNonPairSumNumber: Long) => {
        findContiguousNumbersIdxToSum(inputIndexedSeq, firstNonPairSumNumber) match {
          case Some((start: Int, end: Int)) => {
            val contiguousNumbers = inputIndexedSeq.slice(start, end)
            Some(contiguousNumbers.min + contiguousNumbers.max)
          }
          case None => None
        }
      }
      case None => None
    }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day09.txt"
    val preambleLength = 25
    findEncryptionWeakness(fileName, preambleLength) match {
      case Some(encryptionWeakness: Long) => println(s"The encryption weakness is ${encryptionWeakness}.")
      case None => println("Encryption weakness cannot be calculated.")
    }
  }
}
