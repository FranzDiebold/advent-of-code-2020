/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/12
 * Day 12: Rain Risk
 * Part 2
 */

package day12

import day12.Part1.{DirectionToVectorMap, readInstructions, calculateManhattanDistance}

object Part2 {
  def executeInstructions(instructions: Iterator[Tuple2[Char, Int]]): Tuple2[Int, Int] = {
    instructions
      .foldLeft[Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]](((0, 0), (10, -1))) {
        case (((x: Int, y: Int), (wx: Int, wy: Int)), (action: Char, parameter: Int)) => {
          action match {
            case 'N' | 'S' | 'E' | 'W' => {
              val (wdx: Int, wdy: Int) = DirectionToVectorMap(action)
              ((x, y), (wx + parameter * wdx, wy + parameter * wdy))
            }
            case 'L' | 'R' => {
              val numRawQuarterRotations: Int = (parameter % 360) / 90
              val numRightQuarterRotations: Int = action match {
                case 'L' => 4 - numRawQuarterRotations
                case 'R' => numRawQuarterRotations
              }
              val (nwdx: Int, nwdy: Int) = (1 to numRightQuarterRotations)
                .foldLeft[Tuple2[Int, Int]]((wx, wy)) {
                  case ((cwdx: Int, cwdy: Int), _) => (-1 * cwdy, cwdx)
                }
              ((x, y), (nwdx, nwdy))
            }
            case 'F' => ((x + parameter * wx, y + parameter * wy), (wx, wy))
          }
        }
      }
      ._1
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val instructions: Iterator[Tuple2[Char, Int]] = readInstructions(fileName)
    val finalPosition: Tuple2[Int, Int] = executeInstructions(instructions)
    val manhattanDistance = calculateManhattanDistance(finalPosition)
    println(s"The Manhattan distance (using waypoint) is ${manhattanDistance}.")
  }
}
