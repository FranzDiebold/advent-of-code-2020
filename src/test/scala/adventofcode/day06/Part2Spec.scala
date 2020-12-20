import org.scalatest.funsuite._

import day06.Part2.getNumberOfQuestionsEveryone

class Day06Part2Spec extends AnyFunSuite {
  test("Should get the correct number of questions.") {
    assert(getNumberOfQuestionsEveryone("day06.txt") == 6)
  }
}
