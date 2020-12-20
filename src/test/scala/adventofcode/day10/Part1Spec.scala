import org.scalatest.funsuite._

import day10.Part1.getDifferencesProduct

class Day10Part1Spec extends AnyFunSuite {
  test("Should get the correct product of differences.") {
    assert(getDifferencesProduct("day10.txt") == 35)
  }

  test("Should get the correct product of differences on larger example.") {
    assert(getDifferencesProduct("day10_2.txt") == 220)
  }
}
