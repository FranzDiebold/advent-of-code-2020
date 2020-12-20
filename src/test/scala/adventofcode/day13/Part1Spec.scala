import org.scalatest.funsuite._

import day13.Part1.{readNotes, findEarliestBusToLeave}

class Day13Part1Spec extends AnyFunSuite {
  test("Should get the correct earliest bus to leave.") {
    assert((findEarliestBusToLeave _).tupled(readNotes("day13.txt")) == (59, 5))
  }
}
