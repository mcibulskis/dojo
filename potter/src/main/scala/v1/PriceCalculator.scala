package v1

class PriceCalculator {

  def calculatePrice(books: Seq[Book]): Double = {
    val basePrice = calculateBasePrice(books)

    if (books.distinct.size > 1) basePrice * .95 else basePrice
  }

  private def calculateBasePrice(books: scala.Seq[Book]): Double = {
    8.00 * books.size
  }
}
