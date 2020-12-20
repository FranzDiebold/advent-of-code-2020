/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/12
 * Day 12: Rain Risk
 * Part 1
 */

package day12

import scala.io.Source
import scala.util.matching.Regex

object Part1 {
  val DirectionToVectorMap: Map[Char, Tuple2[Int, Int]] = Map[Char, Tuple2[Int, Int]](
    'N' -> (0, -1),
    'S' -> (0, 1),
    'E' -> (1, 0),
    'W' -> (-1, 0),
  )

  def readInstructions(fileName: String): Iterator[Tuple2[Char, Int]] = {
    val instructionRegex: Regex = """([NSEWLRF])(\d+)""".r
    Source
      .fromResource(fileName)
      .getLines()
      .map({
        case instructionRegex(action: String, parameter: String) => (action(0), parameter.toInt)
      })
  }

  def executeInstructions(instructions: Iterator[Tuple2[Char, Int]]): Tuple2[Int, Int] = {
    instructions
      .foldLeft[Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]](((0, 0), (1, 0))) {
        case (((x: Int, y: Int), (dx: Int, dy: Int)), (action: Char, parameter: Int)) => {
          action match {
            case 'N' | 'S' | 'E' | 'W' => {
              val (tdx: Int, tdy: Int) = DirectionToVectorMap(action)
              ((x + parameter * tdx, y + parameter * tdy), (dx, dy))
            }
            case 'L' | 'R' => {
              val numRawQuarterRotations: Int = (parameter % 360) / 90
              val numRightQuarterRotations: Int = action match {
                case 'L' => 4 - numRawQuarterRotations
                case 'R' => numRawQuarterRotations
              }
              val (ndx: Int, ndy: Int) = (1 to numRightQuarterRotations)
                .foldLeft[Tuple2[Int, Int]]((dx, dy)) {
                  case ((cdx: Int, cdy: Int), _) => (-1 * cdy, cdx)
                }
              ((x, y), (ndx, ndy))
            }
            case 'F' => ((x + parameter * dx, y + parameter * dy), (dx, dy))
          }
        }
      }
      ._1
  }

  def calculateManhattanDistance(position: Tuple2[Int, Int]): Int = position._1.abs + position._2.abs

  def main(args: Array[String]): Unit = {
    val fileName = "day12.txt"
    val instructions: Iterator[Tuple2[Char, Int]] = readInstructions(fileName)
    val finalPosition: Tuple2[Int, Int] = executeInstructions(instructions)
    val manhattanDistance = calculateManhattanDistance(finalPosition)
    println(s"The Manhattan distance is ${manhattanDistance}.")
  }
}
