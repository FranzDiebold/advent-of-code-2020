import org.scalatest.funsuite._

import day01.Part1.getSpecialNumberPair

class Day01Part1Spec extends AnyFunSuite {
  test("Should find correct special number pair.") {
    assert(getSpecialNumberPair("day01.txt", 2020) == Some(Some(1721, 299)))
  }
}
