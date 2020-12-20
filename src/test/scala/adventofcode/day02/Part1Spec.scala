import org.scalatest.funsuite._

import day02.Part1.getNumberOfValidPasswords

class Day02Part1Spec extends AnyFunSuite {
  test("Should get the correct number of valid passwords.") {
    assert(getNumberOfValidPasswords("day02.txt") == 2)
  }
}
