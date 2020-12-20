import org.scalatest.funsuite._

import day09.Part2.findEncryptionWeakness

class Day09Part2Spec extends AnyFunSuite {
  test("Should get the correct encryption weakness.") {
    assert(findEncryptionWeakness("day09.txt", 5) == Some(62))
  }
}
