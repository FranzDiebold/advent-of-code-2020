/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/8
 * Day 8: Handheld Halting
 * Part 1
 */

package day08

import scala.io.Source
import scala.util.matching.Regex

object Part1 {
  def readProgram(fileName: String): IndexedSeq[Option[Tuple2[String, Int]]] = {
    val instructionRegex: Regex = """(nop|acc|jmp) ([+-]\d+)""".r
    Source
      .fromResource(fileName)
      .getLines()
      .map({
        case instructionRegex(operation: String, argument: String) =>
          Some(operation, argument.toInt)
        case _ => None
      })
      .toIndexedSeq
  }

  def runProgram(
    program: IndexedSeq[Option[Tuple2[String, Int]]],
    instructionPointer: Int = 0,
    accumulator: Int = 0,
    previousInstructions: Set[Int] = Set[Int]()
  ): Option[Int] = {
    if (previousInstructions.contains(instructionPointer)) {
      return Some(accumulator)
    }
    program(instructionPointer) match {
      case Some((operation: String, argument: Int)) =>
        operation match {
          case "nop" =>
            runProgram(
              program,
              instructionPointer + 1,
              accumulator,
              previousInstructions + instructionPointer
            )
          case "acc" =>
            runProgram(
              program,
              instructionPointer + 1,
              accumulator + argument,
              previousInstructions + instructionPointer
            )
          case "jmp" =>
            runProgram(
              program,
              instructionPointer + argument,
              accumulator,
              previousInstructions + instructionPointer
            )
          case _ => None
        }
      case None => None
    }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day08.txt"
    val program: IndexedSeq[Option[Tuple2[String, Int]]] = readProgram(fileName)
    runProgram(program) match {
      case Some(programResult: Int) => println(s"The value in the accumulator is ${programResult}.")
      case None                     => println("Something went wrong running the program.")
    }
  }
}
