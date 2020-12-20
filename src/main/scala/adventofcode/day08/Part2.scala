/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/8
 * Day 8: Handheld Halting
 * Part 2
 */

package day08

import day08.Part1.{readProgram}

object Part2 {
  def runProgram(
    program: IndexedSeq[Option[Tuple2[String, Int]]],
    instructionPointer: Int = 0,
    accumulator: Int = 0,
    previousInstructions: Set[Int] = Set[Int]()
  ): Option[Int] = {
    if (instructionPointer == program.size) {
      return Some(accumulator)
    }

    if (previousInstructions.contains(instructionPointer)) {
      return None
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

  def findOutputOfTerminatingProgram(
    program: IndexedSeq[Option[Tuple2[String, Int]]],
    instructionIdxToChange: Int = 0
  ): Option[Int] = {
    if (instructionIdxToChange >= program.size) {
      return None
    }

    val operationChangeMap = Map[String, String]("nop" -> "jmp", "jmp" -> "nop")
    program(instructionIdxToChange) match {
      case Some((operation: String, argument: Int)) =>
        operationChangeMap.keySet.contains(operation) match {
          case true => {
            val updatedProgram =
              program.updated(instructionIdxToChange, Some(operationChangeMap(operation), argument))
            runProgram(updatedProgram) match {
              case Some(programResult: Int) => Some(programResult)
              case None                     => findOutputOfTerminatingProgram(program, instructionIdxToChange + 1)
            }
          }
          case _ => findOutputOfTerminatingProgram(program, instructionIdxToChange + 1)
        }
      case None => None
    }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day08.txt"
    val program: IndexedSeq[Option[Tuple2[String, Int]]] = readProgram(fileName)
    findOutputOfTerminatingProgram(program) match {
      case Some(programResult: Int) =>
        println(s"The value of the accumulator after the program terminates is ${programResult}.")
      case None => println("Could not find a terminating program.")
    }
  }
}
