package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class CreateSubsetsSpec extends FlatSpec with ShouldMatchers {
  val impl: PriceCalculator = new PriceCalculator

  behavior of "Creating subsets of Books"

  it should "should create single subset when only one book in list" in {
    impl.createSubsets(Seq(Book1)) should equal(Seq(Seq(Book1)))
  }
  
  it should "should create single subset when two different books are in list" in {
    impl.createSubsets(Seq(Book1, Book2)) should equal(Seq(Seq(Book1, Book2)))
  }
  
  it should "should create two subsets when multiple of same book in list" in {
    impl.createSubsets(Seq(Book1, Book2, Book1)) should equal(Seq(Seq(Book1, Book2), Seq(Book1)))
  }
  
  it should "should create three subsets when three copies of three books exist" in {
    impl.createSubsets(Seq(Book1, Book2, Book3, Book1, Book2, Book3, Book1, Book2, Book3)) should equal(Seq(Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3)))
  }
}
