import org.scalatest.funsuite._

import day08.Part1.readProgram
import day08.Part2.findOutputOfTerminatingProgram

class Day08Part2Spec extends AnyFunSuite {
  test("Should get the correct accumulator value after the program terminates.") {
    assert(findOutputOfTerminatingProgram(readProgram("day08.txt")) == Some(8))
  }
}
