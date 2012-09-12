package v1

class RedPencil {
  def isRedPencilPromotion(lastPrice: Double, newPrice: Double, daysLastPriceWasEffective: Int): Boolean = {

    if (daysLastPriceWasEffective < 30) return false
    true
  }
}
