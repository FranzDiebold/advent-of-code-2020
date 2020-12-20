/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/13
 * Day 13: Shuttle Search
 * Part 1
 */

package day13

import scala.io.Source

object Part1 {
  def readNotes(fileName: String): Tuple2[Int, Seq[Int]] = {
    val notes: IndexedSeq[String] = Source
      .fromResource(fileName)
      .getLines()
      .toIndexedSeq

    val busIds = notes(1)
      .split(",")
      .filter(_ != "x")
      .map(_.toInt)

    (notes(0).toInt, busIds)
  }

  def findEarliestBusToLeave(earliestBus: Int, busIds: Seq[Int]): Tuple2[Int, Int] = {
    busIds
      .map((busId: Int) => (busId, busId - (earliestBus % busId)))
      .minBy(_._2)
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day13.txt"
    val (earliestBus: Int, busIds: Seq[Int]) = readNotes(fileName)
    val (earliestBusId: Int, waitingTime: Int) = findEarliestBusToLeave(earliestBus, busIds)
    val product = earliestBusId * waitingTime
    println(s"The product is ${product}.")
  }
}
