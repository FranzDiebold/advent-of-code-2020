/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/17
 * Day 17: Conway Cubes
 * Part 2
 */

package day17

import scala.io.Source

import day17.Part1.{ActiveState, InactiveState}

object Part2 {
  def readInitialState(fileName: String): Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = {
    val initialState: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = Map[Int, Map[Int, Map[Int, Map[Int, Char]]]]()
      .withDefault(_ => Map[Int, Map[Int, Map[Int, Char]]]()
        .withDefault(_ => Map[Int, Map[Int, Char]]()
          .withDefault(_ => Map[Int, Char]()
            .withDefault(_ => InactiveState)
          )
        )
      )

    Source
      .fromResource(fileName)
      .getLines()
      .zipWithIndex
      .foldLeft[Map[Int, Map[Int, Map[Int, Map[Int, Char]]]]](initialState)({
        case (state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]], (line: String, y: Int)) => line
          .zipWithIndex
          .foldLeft[Map[Int, Map[Int, Map[Int, Map[Int, Char]]]]](state)({
            case (currentState: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]], (stateValue: Char, x: Int)) => setState(currentState, x, y, 0, 0, stateValue)
          })
      })
  }

  def setState(state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]], x: Int, y: Int, z: Int, w: Int, stateValue: Char): Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = {
    state + (x -> (state(x) + (y -> (state(x)(y) + (z -> (state(x)(y)(z) + (w -> stateValue)))))))
  }

  def getNumberOfActiveNeighborCubes(state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]], x: Int, y: Int, z: Int, w: Int): Int = {
    (for {
      dx <- Seq(-1, 0, 1)
      dy <- Seq(-1, 0, 1)
      dz <- Seq(-1, 0, 1)
      dw <- Seq(-1, 0, 1)
    } yield (dx, dy, dz, dw))
      .filter({
        case (dx: Int, dy: Int, dz: Int, dw: Int) => dx != 0 || dy != 0 || dz != 0 || dw != 0
      })
      .map({
        case (dx: Int, dy: Int, dz: Int, dw: Int) => state(x + dx)(y + dy)(z + dz)(w + dw)
      })
      .count(_ == ActiveState)
  }

  def performCycle(state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]]): Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = {
    (for {
      x <- ((state.keySet.min - 1) to (state.keySet.max + 2))
      y <- ((state(0).keySet.min - 1) to (state(0).keySet.max + 2))
      z <- ((state(0)(0).keySet.min - 1) to (state(0)(0).keySet.max + 2))
      w <- ((state(0)(0)(0).keySet.min - 1) to (state(0)(0)(0).keySet.max + 2))
    } yield (x, y, z, w))
      .foldLeft[Map[Int, Map[Int, Map[Int, Map[Int, Char]]]]](state)({
        case (currentState: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]], (x: Int, y: Int, z: Int, w: Int)) => {
          val numActiveNeighborCubes: Int = getNumberOfActiveNeighborCubes(state, x, y, z, w)
          val newCubeState: Char = (state(x)(y)(z)(w), numActiveNeighborCubes) match {
            case (ActiveState, 2) | (ActiveState, 3) => ActiveState
            case (InactiveState, 3) => ActiveState
            case _ => InactiveState
          }
          setState(currentState, x, y, z, w, newCubeState)
        }
      })
  }

  def countActiveCubes(state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]]): Int = {
    state.values
      .map(_.values
        .map(_.values
          .map(_.values
            .count(_ == ActiveState)
          )
          .sum
        )
        .sum
      )
      .sum
  }

  def performNCycles(state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]], n: Int): Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = {
    if (n == 0) {
      return state
    }
    performNCycles(performCycle(state), n - 1)
  }

  def main(args: Array[String]): Unit = {
    val fileName = "day17.txt"
    val numCycles = 6
    val initialState: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = readInitialState(fileName)
    val state: Map[Int, Map[Int, Map[Int, Map[Int, Char]]]] = performNCycles(initialState, numCycles)
    val numActiveCubes = countActiveCubes(state)
    println(s"${numActiveCubes} cubes are left in the active state (in 4 dimensions) after the ${numCycles}th cycle.")
  }
}
