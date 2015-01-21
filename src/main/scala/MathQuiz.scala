import _root_.MathQuiz.{divide, multiply}

import scala.util.Random

class MathQuiz {
  //TODO get second part of sum and break it out into its components and make a new question
  // a x b = c => a x d x e = c where d x e = b
  // a / b = c => a / d / e = c where d / e = b
  def toQuestion(partsOfSum: IndexedSeq[String], operator: String) :String = {
    val missingPart : String = "?"
    operator match {
      case multiply => formatQuestion(partsOfSum.updated(Random.nextInt(3), missingPart), multiply)
      case divide => formatQuestion(partsOfSum.updated(Random.nextInt(2), missingPart).reverse, divide)
    }
  }

  private def formatQuestion(question: IndexedSeq[String], operand: String) = {
    s"${question(0)} $operand ${question(1)} = ${question(2)}"
  }
}

object MathQuiz extends App {
  val multiply: String = "x"
  val divide : String = "/"

  override def main(args: Array[String]) {
    val timesTables = for {
      i <- 1 until 12
      j <- 1 until 12
    } yield IndexedSeq(Integer.toString(i), Integer.toString(j), Integer.toString(i * j))

    val operators = IndexedSeq(multiply,divide)
    Random.shuffle(timesTables).take(60).zipWithIndex.foreach(timesTable => println(s"${1+timesTable._2}. ${new MathQuiz().toQuestion(timesTable._1, Random.shuffle(operators).head)}"))
  }
}
