import org.scalatest.funsuite._

import day02.Part2.getNumberOfValidPasswords

class Day02Part2Spec extends AnyFunSuite {
  test("Should get the correct number of valid passwords (new interpretation).") {
    assert(getNumberOfValidPasswords("day02.txt") == 1)
  }
}
