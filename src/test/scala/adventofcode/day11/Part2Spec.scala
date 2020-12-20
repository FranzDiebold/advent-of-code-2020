import org.scalatest.funsuite._

import day11.Part1.{readSeatLayout, getTotalNumberOfOccupiedSeats}
import day11.Part2.findStableSeatLayout

class Day11Part2Spec extends AnyFunSuite {
  test("Should get the correct number of occupied seats (visibility).") {
    assert(getTotalNumberOfOccupiedSeats(findStableSeatLayout(readSeatLayout("day11.txt"))) == 26)
  }
}
