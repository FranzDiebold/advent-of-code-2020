/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/3
 * Day 3: Toboggan Trajectory
 * Part 2
 */

package day03

import scala.io.Source

import day03.Part1.{readMap, countTrees}

object Part2 {
  def getNumberOfTreesProduct(fileName: String, slopes: Iterable[Tuple2[Int, Int]]): Long = {
    val map: List[String] = readMap(fileName)
    slopes
      .map({
        case (dx: Int, dy: Int) => countTrees(map, dx, dy)
      })
      .map(_.toLong)
      .reduce(_ * _)
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val slopes = List(
      (1, 1),
      (3, 1),
      (5, 1),
      (7, 1),
      (1, 2)
    )
    val numberOfTreesProduct: Long = getNumberOfTreesProduct(fileName, slopes)

    println(s"The product of the number of trees is ${numberOfTreesProduct}.")
  }
}
