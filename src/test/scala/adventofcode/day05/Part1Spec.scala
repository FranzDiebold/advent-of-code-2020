import org.scalatest.funsuite._

import day05.Part1.getHighestSeatId

class Day05Part1Spec extends AnyFunSuite {
  test("Should get the correct highest seat Id.") {
    assert(getHighestSeatId("day05.txt") == 820)
  }
}
