import org.scalatest.funsuite._

import day07.Part1.{readRules, generateRuleGraph, getNumberOfBagsContainingTargetBag}

class Day07Part1Spec extends AnyFunSuite {
  test("Should get the correct number of bags.") {
    assert(getNumberOfBagsContainingTargetBag(generateRuleGraph(readRules("day07.txt")), "shiny gold") == 4)
  }
}
