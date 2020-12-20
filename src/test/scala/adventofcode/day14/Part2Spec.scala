import org.scalatest.funsuite._

import day14.Part1.{readProgram, sumMemoryValues}
import day14.Part2.runInitializationProgram

class Day14Part2Spec extends AnyFunSuite {
  test("Should get the correct sum of all values in memory (version 2).") {
    assert(sumMemoryValues(runInitializationProgram((readProgram("day14_2.txt")))) == 208)
  }
}
