import org.scalatest.funsuite._

import day16.Part1.readNotes
import day16.Part2.{determineFieldMapping, getTicketFieldValues}

class Day16Part2Spec extends AnyFunSuite {
  test("Should get the correct ticket field values.") {
    val (validRanges , yourTicketValues, nearbyTicketsValues) = readNotes("day16_2.txt")
    val fieldMapping = determineFieldMapping(validRanges, nearbyTicketsValues)
    assert(getTicketFieldValues(yourTicketValues, fieldMapping) == Map("class" -> 12, "row" -> 11, "seat" -> 13))
  }
}
