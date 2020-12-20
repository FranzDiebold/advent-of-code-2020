import org.scalatest.funsuite._

import day15.Part1.getNthMemoryGameNumberSpoken

class Day15Part1Spec extends AnyFunSuite {
  Seq(
    (IndexedSeq(1, 3, 2), 1),
    (IndexedSeq(2, 1, 3), 10),
    (IndexedSeq(1, 2, 3), 27),
    (IndexedSeq(2, 3, 1), 78),
    (IndexedSeq(3, 2, 1), 438),
    (IndexedSeq(3, 1, 2), 1836),
  )
    .foreach({
      case (input: IndexedSeq[Int], expectedOutput: Int) =>
        test(s"Should get the correct 2020th number for (${input.mkString(", ")}).") {
          assert(getNthMemoryGameNumberSpoken(input, 2020) == expectedOutput)
        }
    })
}
