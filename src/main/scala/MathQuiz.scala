import scala.util.Random

class MathQuiz {

  def toQuestion(partsOfSum: IndexedSeq[String] = IndexedSeq("9", "5", "45"), operator: String = "x") :String = {
    operator match {
      case "x" => toMultiplicationQuestion(partsOfSum)
      case "/" => toDivisionQuestion(partsOfSum)
    }
  }

  private def toDivisionQuestion(partsOfSum: IndexedSeq[String]): String = {
    formatQuestion(partsOfSum.updated(Random.nextInt(2), "?").reverse, "/")
  }

  private def toMultiplicationQuestion(partsOfSum: IndexedSeq[String]): String = {
    formatQuestion(partsOfSum.updated(Random.nextInt(3), "?"), "x")
  }

  private def formatQuestion(question: IndexedSeq[String], operand: String) = {
    s"${question(0)} $operand ${question(1)} = ${question(2)}"
  }
}

object MathQuiz extends App {

  override def main(args: Array[String]) {
    val timesTables = for {
      i <- 1 until 12
      j <- 1 until 12
    } yield IndexedSeq(Integer.toString(i), Integer.toString(j), Integer.toString(i * j))

    val operators = IndexedSeq("x","/")
    Random.shuffle(timesTables).take(60).zipWithIndex.foreach(timesTable => println(s"${1+timesTable._2}. ${new MathQuiz().toQuestion(timesTable._1, Random.shuffle(operators).head)}"))
  }
}
