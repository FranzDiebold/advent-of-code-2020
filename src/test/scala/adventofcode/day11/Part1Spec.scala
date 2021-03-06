import org.scalatest.funsuite._

import day11.Part1.{findStableSeatLayout, getTotalNumberOfOccupiedSeats, readSeatLayout}

class Day11Part1Spec extends AnyFunSuite {
  test("Should get the correct number of occupied seats.") {
    assert(getTotalNumberOfOccupiedSeats(findStableSeatLayout(readSeatLayout("day11.txt"))) == 37)
  }
}
