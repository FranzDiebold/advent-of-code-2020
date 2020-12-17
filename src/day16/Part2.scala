/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/16
 * Day 16: Ticket Translation
 * Part 2
 */

package day16

import day16.Part1.{readNotes}

object Part2 {
  def isTicketValid(validRanges: Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]], ticketValues: Seq[Int]): Boolean = {
    ticketValues
      .forall((value: Int) => validRanges.exists({
        case (_, ((from1: Int, to1: Int), (from2: Int, to2: Int))) => (from1 <= value && value <= to1) || (from2 <= value && value <= to2)
      })
    )
  }

  def getMostProbableNextFieldMapping(fieldToPossibleValueIdxsMap: Map[String, Map[Int, Int]], numValidTickets: Int): Tuple2[String, Int] = {
    val mostProbableFieldToValueIdx: Tuple2[String, Seq[Int]] = fieldToPossibleValueIdxsMap
      .map({
        case (field: String, possibleValueIdxsMap: Map[Int, Int]) => {
          val probableFieldValues: Seq[Int] = possibleValueIdxsMap
            .filter(_._2 == numValidTickets)
            .map(_._1)
            .toSeq
          (field, probableFieldValues)
        }
      })
      .toSeq
      .sortBy(_._2.size)
      .head

    (mostProbableFieldToValueIdx._1, mostProbableFieldToValueIdx._2(0))
  }

  def chooseFieldMappings(fieldToPossibleValueIdxsMap: Map[String, Map[Int, Int]], numValidTickets: Int, currentFieldMapping: Map[String, Int] = Map[String, Int]()): Map[String, Int] = {
    if (fieldToPossibleValueIdxsMap.isEmpty) {
      return currentFieldMapping
    }
    val (field: String, valueIdx: Int) = getMostProbableNextFieldMapping(fieldToPossibleValueIdxsMap, numValidTickets)
    val newFieldToPossibleValueIdxsMap: Map[String, Map[Int, Int]] = (fieldToPossibleValueIdxsMap - field)
      .mapValues((possibleValueIdxsMap: Map[Int, Int]) => possibleValueIdxsMap - valueIdx)
    val newFieldMapping: Map[String, Int] = currentFieldMapping + (field -> valueIdx)
    chooseFieldMappings(newFieldToPossibleValueIdxsMap, numValidTickets, newFieldMapping)
  }

  def determineFieldMapping(validRanges: Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]], nearbyTicketsValues: Seq[Seq[Int]]): Map[String, Int] = {
    val validNearbyTicketsValues: Seq[Seq[Int]] = nearbyTicketsValues
      .filter((ticketValues: Seq[Int]) => isTicketValid(validRanges, ticketValues))

    val fieldToPossibleValueIdxsMap: Map[String, Map[Int, Int]] = validNearbyTicketsValues
      .foldLeft[Map[String, Map[Int, Int]]](Map[String, Map[Int, Int]]().withDefault(_ => Map[Int, Int]().withDefault(_ => 0)))({
        case (fieldToValueIdxsMap: Map[String, Map[Int, Int]], ticketValues: Seq[Int]) => ticketValues
          .zipWithIndex
          .foldLeft[Map[String, Map[Int, Int]]](fieldToValueIdxsMap)({
            case (currentFieldToValueIdxsMap: Map[String, Map[Int, Int]], (value: Int, valueIdx: Int)) => validRanges
              .foldLeft[Map[String, Map[Int, Int]]](currentFieldToValueIdxsMap)({
                case (map: Map[String, Map[Int, Int]], (field: String, ((from1: Int, to1: Int), (from2: Int, to2: Int)))) => {
                  if ((from1 <= value && value <= to1) || (from2 <= value && value <= to2)) {
                    map + (field -> (map(field) + (valueIdx -> (map(field)(valueIdx) + 1))))
                  } else {
                    map
                  }
                }
              })
          })
      })

    chooseFieldMappings(fieldToPossibleValueIdxsMap, validNearbyTicketsValues.size)
  }

  def getTicketFieldValues(ticketValues: Seq[Int], fieldMapping: Map[String, Int]): Map[String, Int] = {
    fieldMapping
      .map({
        case (field: String, valueIdx: Int) => (field, ticketValues(valueIdx))
      })
  }

  def getFieldsWithPrefixProduct(ticketFieldValues: Map[String, Int], prefix: String): BigInt = {
    ticketFieldValues
      .filter(_._1.startsWith(prefix))
      .map({
        case (_, value: Int) => BigInt(value)
      })
      .product
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val prefix = "departure"
    val (validRanges: Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]], yourTicketValues: Seq[Int], nearbyTicketsValues: Seq[Seq[Int]]) = readNotes(fileName)
    val fieldMapping: Map[String, Int] = determineFieldMapping(validRanges, nearbyTicketsValues)
    val yourTicketFieldValues: Map[String, Int] = getTicketFieldValues(yourTicketValues, fieldMapping)
    val productDepartureFields: BigInt = getFieldsWithPrefixProduct(yourTicketFieldValues, prefix)
    println(s"The product of the six values starting with '${prefix}' is ${productDepartureFields}.")
  }
}
