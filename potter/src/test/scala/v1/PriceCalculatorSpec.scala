package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class PriceCalculatorSpec extends FlatSpec with ShouldMatchers {
  val impl: PriceCalculator = new PriceCalculator

  behavior of "Pricing baskets of books"

  it should "price a single copy of any book at 8 EUR" in {
    impl.calculatePrice(List(Book1)) should equal(8.00)
  }

  it should "price two copies of the same book for a total of 16 EUR" in {
    impl.calculatePrice(List(Book1, Book1)) should equal(16.00)
  }

  it should "price a single copy of two different books for a total of 15.20" in {
    impl.calculatePrice(List(Book1, Book2)) should equal(15.20)
  }
}
