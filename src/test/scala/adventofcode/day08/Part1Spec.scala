import org.scalatest.funsuite._

import day08.Part1.{readProgram, runProgram}

class Day08Part1Spec extends AnyFunSuite {
  test("Should get the correct accumulator value.") {
    assert(runProgram(readProgram("day08.txt")) == Some(5))
  }
}
