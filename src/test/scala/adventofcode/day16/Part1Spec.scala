import org.scalatest.funsuite._

import day16.Part1.{readNotes, getTicketScanningErrorRate}

class Day16Part1Spec extends AnyFunSuite {
  test("Should get the correct scanning error rate.") {
    val (validRanges, _, nearbyTicketsValues) = readNotes("day16.txt")
    assert(getTicketScanningErrorRate(validRanges, nearbyTicketsValues) == 71)
  }
}
