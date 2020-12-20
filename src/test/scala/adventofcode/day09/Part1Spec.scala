import org.scalatest.funsuite._

import day09.Part1.{findFirstNonPairSumNumber, readInput}

class Day09Part1Spec extends AnyFunSuite {
  test("Should get the correct first non pair sum number.") {
    assert(findFirstNonPairSumNumber(readInput("day09.txt"), 5) == Some(127))
  }
}
