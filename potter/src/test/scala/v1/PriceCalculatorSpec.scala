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

  it should "price a single copy of three different books for a total of 21.60" in {
    impl.calculatePrice(List(Book1, Book2, Book3)) should equal(21.60)
  }

  it should "price a single copy of four different books for a total of 25.60" in {
    impl.calculatePrice(List(Book1, Book2, Book3, Book4)) should equal(25.60)
  }

  it should "price a single copy of five different books for a total of 30.00" in {
    impl.calculatePrice(List(Book1, Book2, Book3, Book4, Book5)) should equal(30.00)
  }

  it should "price five copies of the same book for a total of 40 EUR" in {
    impl.calculatePrice(List(Book1, Book1, Book1, Book1, Book1)) should equal(40.00)
  }

  it should "create a group of 2 and a group of 1" in {
    impl.calculatePrice(List(Book1, Book1, Book2)) should equal(23.20)
  }

  it should "create a group of 2 and two groups of 1" in {
    impl.calculatePrice(List(Book1, Book1, Book2, Book1)) should equal(31.20)

  }
  it should "create a group of 2 and a group of 2" in {
    impl.calculatePrice(List(Book1, Book1, Book2, Book2)) should equal(30.40)
  }

  it should "handle 1, 1, 2, 2, 3, 3" in {
    impl.calculatePrice(List(Book1, Book1, Book2, Book2, Book3, Book3)) should equal(43.20)
  }

  it should "handle 1, 1, 2, 2, 3, 3, 4, 5 (optimistic grouping... not aggresive grouping" in {
    impl.calculatePrice(List(Book1, Book1, Book2, Book2, Book3, Book3, Book4, Book5)) should equal(51.20)
  }

  it should "handle 1, 2, 2, 3, 4, 5" in {
    impl.calculatePrice(List(Book1, Book2, Book2, Book3, Book4, Book5)) should equal(38.00)
  }
  
  it should "handle 1, 2, 3, 1, 2, 3, 1, 2, 3 (false positive)" in {
    impl.calculatePrice(List(Book1, Book2, Book3, Book1, Book2, Book3, Book1, Book2, Book3)) should equal(64.80)
  }
}
