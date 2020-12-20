import org.scalatest.funsuite._

import day10.Part2.getDifferencesProduct

class Day10Part2Spec extends AnyFunSuite {
  test("Should get the correct product of differences.") {
    assert(getDifferencesProduct("day10.txt") == 8)
  }

  test("Should get the correct product of differences on larger example.") {
    assert(getDifferencesProduct("day10_2.txt") == 19208)
  }
}
