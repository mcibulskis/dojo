package v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class RedPencilSpec extends FlatSpec with ShouldMatchers {
  val impl = new RedPencil

  behavior of "Determining if a price reduction should be flagged as a red pencil promotion"

  it should "return false if the last price was effective for fewer than 30 days" in {
    impl.isRedPencilPromotion(12.00, 10.00, 29) should equal(false)
  }

  it should "return true if the last price was effective for at least 30 days" in {
    impl.isRedPencilPromotion(12.00, 10.00, 30) should equal(true)
  }
}
