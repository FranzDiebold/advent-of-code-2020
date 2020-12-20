import org.scalatest.funsuite._

import day07.Part1.readRules
import day07.Part2.{generateRuleTree, getNumberOfBagsTargetBagContain}

class Day07Part2Spec extends AnyFunSuite {
  test("Should get the correct number of bags.") {
    assert(getNumberOfBagsTargetBagContain(generateRuleTree(readRules("day07.txt")), "shiny gold") == 32)
  }

  test("Should get the correct number of bags in other example.") {
    assert(getNumberOfBagsTargetBagContain(generateRuleTree(readRules("day07_2.txt")), "shiny gold") == 126)
  }
}
