/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/11
 * Day 11: Seating System
 * Part 2
 */

package day11

import day11.Part1.{Floor, EmptySeat, OccupiedSeat, readSeatLayout, getStateAtLocation, getTotalNumberOfOccupiedSeats}

object Part2 {
  def getVisibleSeatInDirection(seatLayout: IndexedSeq[String], x: Int, y: Int, dx: Int, dy: Int): Int = {
    getStateAtLocation(seatLayout, x + dx, y + dy) match {
      case Floor => getVisibleSeatInDirection(seatLayout, x + dx, y + dy, dx, dy)
      case state: Char => state
    }
  }

  def getNumberOfOccupiedVisibleSeats(seatLayout: IndexedSeq[String], x: Int, y: Int): Int = {
    Seq(
      (0, -1),
      (1, -1),
      (1, 0),
      (1, 1),
      (0, 1),
      (-1, 1),
      (-1, 0),
      (-1, -1)
    )
      .map({
        case (dx: Int, dy: Int) => getVisibleSeatInDirection(seatLayout, x, y, dx, dy)
      })
      .count(_ == OccupiedSeat)
  }

  def updateSeatLayout(seatLayout: IndexedSeq[String]): IndexedSeq[String] = {
    seatLayout
      .zipWithIndex
      .map({
        case (line: String, y: Int) => line
        .zipWithIndex
        .map({
            case (state: Char, x: Int) => {
            val numberOfOccupiedVisibleSeats = getNumberOfOccupiedVisibleSeats(seatLayout, x, y)
            state match {
                case Floor => Floor
                case EmptySeat => {
                  if (numberOfOccupiedVisibleSeats == 0) {
                    OccupiedSeat
                  } else {
                    EmptySeat
                  }
                }
                case OccupiedSeat => {
                  if (numberOfOccupiedVisibleSeats >= 5) {
                    EmptySeat
                  } else {
                    OccupiedSeat
                  }
                }
            }
            }
        })
        .mkString
      })
      .toIndexedSeq
  }

  def findStableSeatLayout(seatLayout: IndexedSeq[String]): IndexedSeq[String] = {
    val updatedSeatLayout = updateSeatLayout(seatLayout)
    if (updatedSeatLayout.equals(seatLayout)) {
      updatedSeatLayout
    } else {
      findStableSeatLayout(updatedSeatLayout)
    }
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val seatLayout: IndexedSeq[String] = readSeatLayout(fileName)
    val stableSeatLayout: IndexedSeq[String] = findStableSeatLayout(seatLayout)
    val numOccupiedSeats = getTotalNumberOfOccupiedSeats(stableSeatLayout)
    println(s"In the stable seat layout (visibility) ${numOccupiedSeats} seats end up occupied.")
  }
}
