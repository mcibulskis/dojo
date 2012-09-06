package v1

import collection.mutable.ListBuffer

class PriceCalculator {

  def calculatePrice(books: Seq[Book]): Double = {
    val sequence = createSubsets(books)
    0.0

//    val x = sequence.foldLeft(0)((r, c) => calculateSubsetPrice(sequence))
//    x
  }

  def createSubsets(books: Seq[Book]): Seq[Seq[Book]] = {
    var buffer = new ListBuffer[Seq[Book]]
    val seq1 = books.distinct.toList
    buffer += seq1
    val seq2 = books.toList -- seq1
    buffer += seq2

    buffer.toList
  }

  def calculateSubsetPrice(books: Seq[Book]): Double = {
    val basePrice = calculateBasePrice(books)

    (books.distinct.size) match {
      case (5) => basePrice * .75
      case (4) => basePrice * .80
      case (3) => basePrice * .90
      case (2) => basePrice * .95
      case (_) => basePrice
    }
  }

  private def calculateBasePrice(books: Seq[Book]): Double = {
    8.00 * books.size
  }
}
