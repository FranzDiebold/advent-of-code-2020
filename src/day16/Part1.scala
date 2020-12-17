/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/16
 * Day 16: Ticket Translation
 * Part 1
 */

package day16

import scala.io.Source
import scala.util.matching.Regex

object Part1 {
  def splitIterator[T](iter: Iterator[T], separator: T) = new Iterator[Seq[T]] {
    def hasNext = iter.hasNext
    def next() = iter.takeWhile(_ != separator).toSeq
  }

  def readNotes(fileName: String): Tuple3[Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]], Seq[Int], Seq[Seq[Int]]] = {
    val linesIterator: Iterator[String] = Source
      .fromFile(fileName)
      .getLines()

    val parts: Seq[Seq[String]] = splitIterator(linesIterator, "").toSeq
    parts.foreach(x => x.foreach { _ => })

    val validRangeRegex: Regex = """(.+): (\d+)-(\d+) or (\d+)-(\d+)""".r
    val validRanges: Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]] = parts(0)
      .map({
        case validRangeRegex(name: String, from1: String, to1: String, from2: String, to2: String) => (name, ((from1.toInt, to1.toInt), (from2.toInt, to2.toInt)))
      })

    val yourTicketValues: Seq[Int] = parts(1)(1)
      .split(",")
      .map(_.toInt)

    val nearbyTicketsValues: Seq[Seq[Int]] = parts(2)
      .drop(1)
      .map((valuesString: String) => valuesString
        .split(",")
        .map(_.toInt)
        .toSeq
      )

    (validRanges, yourTicketValues, nearbyTicketsValues)
  }

  def getTicketScanningErrorRate(validRanges: Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]], nearbyTicketsValues: Seq[Seq[Int]]): BigInt = {
    nearbyTicketsValues
      .map((ticketValues: Seq[Int]) => ticketValues
        .filter((value: Int) => !validRanges.exists({
            case (_, ((from1: Int, to1: Int), (from2: Int, to2: Int))) => (from1 <= value && value <= to1) || (from2 <= value && value <= to2)
          })
        )
        .sum
      )
      .sum
  }

  def main(args: Array[String]): Unit = {
    val fileName = "input.txt"
    val (validRanges: Seq[Tuple2[String, Tuple2[Tuple2[Int, Int], Tuple2[Int, Int]]]], _, nearbyTicketsValues: Seq[Seq[Int]]) = readNotes(fileName)
    val ticketScanningErrorRate: BigInt = getTicketScanningErrorRate(validRanges, nearbyTicketsValues)
    println(s"The ticket scanning error rate is ${ticketScanningErrorRate}.")
  }
}
