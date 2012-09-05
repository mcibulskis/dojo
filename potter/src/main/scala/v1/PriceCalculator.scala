package v1

class PriceCalculator {

  def calculatePrice(books: Seq[Book]): Double = {
    val basePrice = calculateBasePrice(books)

    (books.distinct.size) match {
      case(5) => basePrice * .75
      case(4) => basePrice * .80
      case(3) => basePrice * .90
      case(2) => basePrice * .95
      case(_) => basePrice
    }
  }

  private def calculateBasePrice(books: scala.Seq[Book]): Double = {
    8.00 * books.size
  }
}
