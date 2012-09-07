package v1

import collection.mutable.ListBuffer

class PriceCalculator {

  def calculatePrice(books: Seq[Book]): Double = {
    val sequence = createSubsets(books)
    println(sequence)
    
    val x = sequence.foldLeft(0.0)((r, c) => r + calculateSubsetPrice(c))

    ("%09.3f".format(x)).toDouble

  }

  def createSubsets(books: Seq[Book]): Seq[Seq[Book]] = {
    var buffer = new ListBuffer[Seq[Book]]
    val seq1 = books.distinct.toList
    buffer += seq1

    var seq2 = seq1.foldLeft(books)((r, c) => removeBook(c, r.toList))
    if (seq2.size > 0) {
      buffer += seq2
    }
    buffer.toList
  }

  def removeBook(num: Book, list: List[Book]) = list diff List(num)

  def calculateSubsetPrice(books: Seq[Book]): Double = {
    val basePrice = calculateBasePrice(books)

    val adjustedPrice =
      (books.distinct.size) match {
        case (5) => basePrice * .75
        case (4) => basePrice * .80
        case (3) => basePrice * .90
        case (2) => basePrice * .95
        case (_) => basePrice
      }
    
    adjustedPrice
  }

  private def calculateBasePrice(books: Seq[Book]): Double = {
    8.00 * books.size
  }
}
