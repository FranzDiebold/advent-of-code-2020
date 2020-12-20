import org.scalatest.funsuite._

import day17.Part1.{countActiveCubes, performNCycles, readInitialState}

class Day17Part1Spec extends AnyFunSuite {
  test("Should get the correct number of active cubes.") {
    assert(countActiveCubes(performNCycles(readInitialState("day17.txt"), 6)) == 112)
  }
}
