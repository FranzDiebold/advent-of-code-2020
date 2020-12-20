import org.scalatest.funsuite._

import day01.Part2.getSpecialNumberTriple

class Day01Part2Spec extends AnyFunSuite {
  test("Should find correct number triple.") {
    assert(getSpecialNumberTriple("day01.txt", 2020) == Some(Some(979, 366, 675)))
  }
}
