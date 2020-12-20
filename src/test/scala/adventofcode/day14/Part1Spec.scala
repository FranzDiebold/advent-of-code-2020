import org.scalatest.funsuite._

import day14.Part1.{readProgram, runInitializationProgram, sumMemoryValues}

class Day14Part1Spec extends AnyFunSuite {
  test("Should get the correct sum of all values in memory.") {
    assert(sumMemoryValues(runInitializationProgram((readProgram("day14.txt")))) == 165)
  }
}
