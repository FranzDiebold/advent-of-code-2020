import org.scalatest.funsuite._

import day12.Part1.{readInstructions, calculateManhattanDistance}
import day12.Part2.executeInstructions

class Day12Part2Spec extends AnyFunSuite {
  test("Should get the correct Manhattan distance using waypoint.") {
    assert(calculateManhattanDistance(executeInstructions(readInstructions("day12.txt"))) == 286)
  }
}
