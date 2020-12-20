import org.scalatest.funsuite._

import day04.Part1.getNumberOfValidPassports

class Day04Part1Spec extends AnyFunSuite {
  test("Should get the correct number of valid passports.") {
    assert(getNumberOfValidPassports("day04.txt") == 2)
  }
}
