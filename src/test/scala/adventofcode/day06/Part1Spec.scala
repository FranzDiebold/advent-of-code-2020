import org.scalatest.funsuite._

import day06.Part1.getNumberOfQuestionsAnyone

class Day06Part1Spec extends AnyFunSuite {
  test("Should get the correct number of questions.") {
    assert(getNumberOfQuestionsAnyone("day06.txt") == 11)
  }
}
