/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/3
 * Day 3: Toboggan Trajectory
 * Part 1
 */

package day03

import scala.io.Source

object Part1 {
  val Tree = '#'
  val OpenSquare = '.'

  def readMap(fileName: String): List[String] = {
    Source
      .fromResource(fileName)
      .getLines()
      .toList
  }

  def getObjectAtLocation(map: List[String], x: Int, y: Int): Char = {
    val width = map(0).length
    map(y)(x % width)
  }

  def countTrees(map: List[String], dx: Int, dy: Int): Int = {
    (0 to map.length - 1 by dy).zipWithIndex
      .count({ case (y: Int, idx: Int) =>
        getObjectAtLocation(map, idx * dx, y) == Tree
      })
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day03.txt"
    val map: List[String] = readMap(fileName)

    val numTrees = countTrees(map, 3, 1)
    println(s"The number of trees is ${numTrees}.")
  }
}
