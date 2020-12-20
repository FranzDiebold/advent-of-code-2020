/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/7
 * Day 7: Handy Haversacks
 * Part 2
 */

package day07

import day07.Part1.{readRules}

object Part2 {
  def generateRuleTree(rules: Iterator[Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]]): Map[String, Set[Tuple2[String, Int]]] = {
    rules
      .foldLeft(Map[String, Set[Tuple2[String, Int]]]().withDefaultValue(Set[Tuple2[String, Int]]()))(
        (ruleTree: Map[String, Set[Tuple2[String, Int]]], ruleOption: Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]) => {
          ruleOption match {
            case Some((outerBag: String, innerBags: Seq[Option[Tuple2[String, Int]]])) => {
              innerBags
                .foldLeft(ruleTree)((tree: Map[String, Set[Tuple2[String, Int]]], innerBagOption: Option[Tuple2[String, Int]]) => {
                  innerBagOption match {
                    case Some((innerBagName: String, innerBagCount: Int)) => tree + (outerBag -> (tree(outerBag) + ((innerBagName, innerBagCount))))
                    case _ => tree
                  }
                })
            }
            case _ => ruleTree
          }
      })
  }

  def getNumberOfBagsTargetBagContain(ruleTree: Map[String, Set[Tuple2[String, Int]]], targetBag: String): Int = {
    ruleTree(targetBag).toSeq match {
      case Nil => 0
      case containsBags: Seq[Tuple2[String, Int]] =>
        containsBags
          .map({
            case (bagName: String, numBags: Int) => (1 + getNumberOfBagsTargetBagContain(ruleTree, bagName)) * numBags
          })
          .sum
    }
  }

  def main(args: Array[String]): Unit = {
    val targetBag = "shiny gold"
    val fileName = "day07.txt"
    val rules: Iterator[Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]] = readRules(fileName)
    val ruleTree: Map[String, Set[Tuple2[String, Int]]] = generateRuleTree(rules)
    val numBags: Int = getNumberOfBagsTargetBagContain(ruleTree, targetBag)
    println(s"${numBags} individual bags are required inside the '${targetBag}' bag.")
  }
}
