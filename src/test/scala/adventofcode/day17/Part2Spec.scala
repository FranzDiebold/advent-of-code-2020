import org.scalatest.funsuite._

import day17.Part2.{readInitialState, performNCycles, countActiveCubes}

class Day17Part2Spec extends AnyFunSuite {
  test("Should get the correct number of active cubes.") {
    assert(countActiveCubes(performNCycles(readInitialState("day17.txt"), 6)) == 848)
  }
}
