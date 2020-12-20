import org.scalatest.funsuite._

import day04.Part2.getNumberOfValidPassports

class Day04Part2Spec extends AnyFunSuite {
  test("Should detect all valid passports.") {
    assert(getNumberOfValidPassports("day04_valid.txt") == 4)
  }

  test("Should detect all invalid passports.") {
    assert(getNumberOfValidPassports("day04_invalid.txt") == 0)
  }
}
