/**
 * Advent of Code 2020
 * https://adventofcode.com/2020/day/7
 * Day 7: Handy Haversacks
 * Part 1
 */

package day07

import scala.io.Source
import scala.util.matching.Regex

object Part1 {
  def readRules(fileName: String): Iterator[Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]] = {
    val ruleRegex: Regex = """(.*) bags contain (.*)\.""".r
    val innerBagsRegex: Regex = """(\d*) (.+) bags?""".r
    Source
      .fromResource(fileName)
      .getLines()
      .map({
        case ruleRegex(outerBag: String, innerBags: String) => innerBags match {
          case "no other bags" => Some(outerBag, Seq[Option[Tuple2[String, Int]]]())
          case _ => {
            val bagContent: Seq[Option[Tuple2[String, Int]]] = innerBags
              .split(", ")
              .map({
                case innerBagsRegex(number: String, color: String) => Some(color, number.toInt)
                case _ => None
              })
            Some(outerBag, bagContent)
          }
        }
        case _ => None
      })
  }

  def generateRuleGraph(rules: Iterator[Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]]): Map[String, Set[Tuple2[String, Int]]] = {
    rules
      .foldLeft(Map[String, Set[Tuple2[String, Int]]]().withDefaultValue(Set[Tuple2[String, Int]]()))(
        (ruleGraph: Map[String, Set[Tuple2[String, Int]]], ruleOption: Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]) => {
          ruleOption match {
            case Some((outerBag: String, innerBags: Seq[Option[Tuple2[String, Int]]])) => {
              innerBags
                .foldLeft(ruleGraph)((graph: Map[String, Set[Tuple2[String, Int]]], innerBagOption: Option[Tuple2[String, Int]]) => {
                  innerBagOption match {
                    case Some((innerBagName: String, innerBagCount: Int)) => graph + (innerBagName -> (graph(innerBagName) + ((outerBag, innerBagCount))))
                    case _ => graph
                  }
                })
            }
            case _ => ruleGraph
          }
      })
  }

  def getBagsContainingTargetBag(ruleGraph: Map[String, Set[Tuple2[String, Int]]], targetBag: String): Set[String] = {
    ruleGraph(targetBag).toSeq match {
      case Nil => Set[String]()
      case containingBags: Seq[Tuple2[String, Int]] =>
        containingBags
          .map({
            case (bagName: String, _) => getBagsContainingTargetBag(ruleGraph, bagName) + bagName
          })
          .reduce(_ ++ _)
    }
  }

  def getNumberOfBagsContainingTargetBag(ruleGraph: Map[String, Set[Tuple2[String, Int]]], targetBag: String): Int = {
    getBagsContainingTargetBag(ruleGraph, targetBag).size
  }

  def main(args: Array[String]): Unit = {
    val targetBag = "shiny gold"
    val fileName = "day07.txt"
    val rules: Iterator[Option[Tuple2[String, Seq[Option[Tuple2[String, Int]]]]]] = readRules(fileName)
    val ruleGraph: Map[String, Set[Tuple2[String, Int]]] = generateRuleGraph(rules)
    val numBags: Int = getNumberOfBagsContainingTargetBag(ruleGraph, targetBag)
    println(s"${numBags} bag colors can eventually contain at least one '${targetBag}' bag.")
  }
}
