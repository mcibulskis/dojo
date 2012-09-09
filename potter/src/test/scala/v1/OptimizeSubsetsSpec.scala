package v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class OptimizeSubsetsSpec extends FlatSpec with ShouldMatchers {
  val impl = new PriceCalculator

  behavior of "Optimization of subset groupings"

  it should "convert a grouping of 5 and 3 to a grouping of 4 and 4" in {
    impl.optimizeSubsets(Seq(Seq(Book1, Book2, Book3), Seq(Book1, Book2, Book3, Book4, Book5))) should equal(Seq(Seq(Book1, Book2, Book3, Book5), Seq(Book1, Book2, Book3, Book4)))
  }
}
