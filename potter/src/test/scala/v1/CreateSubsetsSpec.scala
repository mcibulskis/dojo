package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class CreateSubsetsSpec extends FlatSpec with ShouldMatchers {
  val impl: PriceCalculator = new PriceCalculator

  behavior of "Creating subsets of Books"

  it should "create single subset when only one book in list" in {
    impl.createSubsets(Seq(Book1)) should equal(Seq(Seq(Book1)))
  }

  it should "create no subsets when no books are in the list" in {
    impl.createSubsets(Seq()) should equal(Seq())
  }

  it should "create four subsets when four of the same book in the list" in {
    impl.createSubsets(Seq(Book1, Book1, Book1, Book1)) should equal(Seq(Seq(Book1),Seq(Book1),Seq(Book1),Seq(Book1)))
  }

  it should "create five subsets when five of the same book in the list" in {
    impl.createSubsets(Seq(Book1, Book1, Book1, Book1, Book1)) should equal(Seq(Seq(Book1),Seq(Book1),Seq(Book1),Seq(Book1), Seq(Book1)))
  }

  it should "create five subsets of two books" in {
    impl.createSubsets(Seq(Book1, Book2, Book1, Book2, Book1, Book2, Book1, Book2, Book1, Book2)) should equal(Seq(Seq(Book1, Book2),Seq(Book1, Book2),Seq(Book1, Book2),Seq(Book1, Book2), Seq(Book1, Book2)))
  }

  it should "create single subset when two different books are in list" in {
    impl.createSubsets(Seq(Book1, Book2)) should equal(Seq(Seq(Book1, Book2)))
  }
  
  it should "create two subsets when multiple of same book in list" in {
    impl.createSubsets(Seq(Book1, Book2, Book1)) should equal(Seq(Seq(Book1, Book2), Seq(Book1)))
  }
  
  it should "create two subsets when two copies of two books exist" in {
    impl.createSubsets(Seq(Book1, Book2, Book1, Book2)) should equal(Seq(Seq(Book1, Book2), Seq(Book1, Book2)))
  }

  it should "create three subsets when three copies of three books exist" in {
    impl.createSubsets(Seq(Book1, Book2, Book3, Book1, Book2, Book3, Book1, Book2, Book3)) should equal(Seq(Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3)))
  }

  it should "create four subsets when four copies of three books exist" in {
    impl.createSubsets(Seq(Book1, Book2, Book3, Book1, Book2, Book3, Book1, Book2, Book3, Book1, Book2, Book3)) should equal(Seq(Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3)))
  }
}
