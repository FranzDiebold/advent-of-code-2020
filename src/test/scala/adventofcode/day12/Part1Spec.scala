import org.scalatest.funsuite._

import day12.Part1.{calculateManhattanDistance, executeInstructions, readInstructions}

class Day12Part1Spec extends AnyFunSuite {
  test("Should get the correct Manhattan distance.") {
    assert(calculateManhattanDistance(executeInstructions(readInstructions("day12.txt"))) == 25)
  }
}
