import org.scalatest.funsuite._

import day15.Part2.getNthMemoryGameNumberSpoken

class Day15Part2Spec extends AnyFunSuite {
  test("Should get the correct 30000000th number for (0, 3, 6).") {
    assert(getNthMemoryGameNumberSpoken(IndexedSeq(0, 3, 6), 30000000) == 175594)
  }
}
