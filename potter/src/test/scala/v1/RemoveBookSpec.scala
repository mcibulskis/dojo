package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class RemoveBookSpec extends FlatSpec with ShouldMatchers {
  val impl: PriceCalculator = new PriceCalculator

  behavior of "Removing book from list"

  it should "should remove book from list" in {
    impl.removeBook(Book1, List(Book1, Book2, Book3)) should equal(List(Book2, Book3))
  }

  it should "should return same list if Book requested to be removed does not exist" in {
    impl.removeBook(Book4, List(Book1, Book2, Book3)) should equal(List(Book1, Book2, Book3))
  }

  it should "should only remove single copy from list if Book exists in list multiple times" in {
    impl.removeBook(Book1, List(Book1, Book1, Book2, Book3)) should equal(List(Book1, Book2, Book3))
  }

}
