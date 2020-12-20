import org.scalatest.funsuite._

import day03.Part2.getNumberOfTreesProduct

class Day03Part2Spec extends AnyFunSuite {
  test("Should get the correct number trees product.") {
    val slopes = List(
      (1, 1),
      (3, 1),
      (5, 1),
      (7, 1),
      (1, 2)
    )
    assert(getNumberOfTreesProduct("day03.txt", slopes) == 336)
  }
}
