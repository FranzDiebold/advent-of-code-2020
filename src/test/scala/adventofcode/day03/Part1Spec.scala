import org.scalatest.funsuite._

import day03.Part1.{readMap, countTrees}

class Day03Part1Spec extends AnyFunSuite {
  test("Should get the correct number trees.") {
    assert(countTrees(readMap("day03.txt"), 3, 1) == 7)
  }
}
