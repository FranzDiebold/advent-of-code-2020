/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/11
 * Day 11: Seating System
 * Part 1
 */

package day11

import scala.io.Source

object Part1 {
  val OutsideLayout = 'O'
  val Floor = '.'
  val EmptySeat = 'L'
  val OccupiedSeat = '#'
 
  def readSeatLayout(fileName: String): IndexedSeq[String] = {
    Source
      .fromResource(fileName)
      .getLines()
      .toIndexedSeq
  }

  def getStateAtLocation(seatLayout: IndexedSeq[String], x: Int, y: Int): Char = {
    val height = seatLayout.size
    val width = seatLayout(0).size
    if (x < 0 || x >= width || y < 0 || y >= height) {
      OutsideLayout
    } else {
      seatLayout(y)(x)
    }
  }

  def getNumberOfOccupiedAdjacentSeats(seatLayout: IndexedSeq[String], x: Int, y: Int): Int = {
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
        case (dx: Int, dy: Int) => getStateAtLocation(seatLayout, x + dx, y + dy)
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
              val numberOfOccupiedAdjacentSeats = getNumberOfOccupiedAdjacentSeats(seatLayout, x, y)
              state match {
                case Floor => Floor
                case EmptySeat => {
                  if (numberOfOccupiedAdjacentSeats == 0) {
                    OccupiedSeat
                  } else {
                    EmptySeat
                  }
                }
                case OccupiedSeat => {
                  if (numberOfOccupiedAdjacentSeats >= 4) {
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

  def getTotalNumberOfOccupiedSeats(seatLayout: IndexedSeq[String]): Int = {
    seatLayout
      .map((line: String) => line
        .count(_ == OccupiedSeat)
      )
      .sum
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day11.txt"
    val seatLayout: IndexedSeq[String] = readSeatLayout(fileName)
    val stableSeatLayout: IndexedSeq[String] = findStableSeatLayout(seatLayout)
    val numOccupiedSeats = getTotalNumberOfOccupiedSeats(stableSeatLayout)
    println(s"In the stable seat layout ${numOccupiedSeats} seats end up occupied.")
  }
}
