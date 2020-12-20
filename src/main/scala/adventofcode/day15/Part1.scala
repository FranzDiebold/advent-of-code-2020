/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/15
 * Day 15: Rambunctious Recitation
 * Part 1
 */

package day15

object Part1 {
  def playMemoryGame(input: IndexedSeq[Int]): Iterator[Int] = {
    Iterator
      .from(1)
      .scanLeft[Tuple2[Int, Map[Int, Seq[Int]]]](
        -1,
        Map[Int, Seq[Int]]().withDefault(_ => Seq[Int]())
      )({
        case ((lastNumber: Int, memory: Map[Int, Seq[Int]]), turn: Int) => {
          val nextNumber = if (turn <= input.size) {
            input(turn - 1)
          } else {
            memory(lastNumber) match {
              case _ :+ secondLast :+ last => last - secondLast
              case _                       => 0
            }
          }
          (nextNumber, memory + (nextNumber -> (memory(nextNumber) :+ turn)))
        }
      })
      .map(_._1)
      .drop(1)
  }

  def getNthElementOfIterator[T](iterator: Iterator[T], n: Int): T = iterator.drop(n - 1).next

  def getNthMemoryGameNumberSpoken(input: IndexedSeq[Int], n: Int): Int =
    getNthElementOfIterator(playMemoryGame(input), n)

  def main(args: Array[String]): Unit = {
    val input = IndexedSeq(1, 17, 0, 10, 18, 11, 6)
    val n = 2020
    val nthNumberSpoken = getNthMemoryGameNumberSpoken(input, n)
    println(s"The ${n}th number spoken will be ${nthNumberSpoken}.")
  }
}
