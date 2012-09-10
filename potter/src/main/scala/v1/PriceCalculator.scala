package v1

import collection.mutable.ListBuffer

class PriceCalculator {

  def calculatePrice(books: Seq[Book]): Double = {
    val bookGroupings = createSubsets(books)
    val optimizedBookGroupings = optimizeSubsets(bookGroupings)

    val x = optimizedBookGroupings.foldLeft(0.0)((r, c) => r + calculateSubsetPrice(c))

    ("%09.3f".format(x)).toDouble

  }

  def createSubsets(books: Seq[Book]): Seq[Seq[Book]] = {
    if (books.isEmpty) return Seq()

    var buffer = new ListBuffer[Seq[Book]]
    val seq1 = books.distinct.toList
    buffer += seq1

    var seq2 = seq1.foldLeft(books)((r, c) => removeBook(c, r.toList))
    if (! seq2.isEmpty) {
      buffer.appendAll(createSubsets(seq2))
    }

    buffer.toList
  }

  def optimizeSubsets(subsets: Seq[Seq[Book]]): Seq[Seq[Book]] = {
    val sizeGroupedSubsets = subsets.groupBy(sequence => sequence.size)
    var buffer = new ListBuffer[Seq[Book]]
    sizeGroupedSubsets.keys.filterNot(key => (key == 3 || key == 5)).foreach {
      key =>
        buffer ++= sizeGroupedSubsets(key)
    }

    val threeGroupSize = safeSize(sizeGroupedSubsets, 3)
    val fiveGroupSize = safeSize(sizeGroupedSubsets, 5)
    val endIndex = math.max(threeGroupSize, fiveGroupSize) - 1
    (0 to endIndex).foreach {
      index =>
        val threeGroup = safeGetAtIndex(sizeGroupedSubsets, 3, index)
        val fiveGroup = safeGetAtIndex(sizeGroupedSubsets, 5, index)
        val rearrangedGroups = attemptToShiftGroupingsOfFiveAndThreeToTwoFours(fiveGroup, threeGroup)
        buffer ++= rearrangedGroups
    }

    buffer.toList
  }

  private def attemptToShiftGroupingsOfFiveAndThreeToTwoFours(fiveGroup: Seq[Book], threeGroup: Seq[Book]): Seq[Seq[Book]] = {
    if (fiveGroup == null && threeGroup == null) return Seq()
    if (fiveGroup == null) return Seq(threeGroup)
    if (threeGroup == null) return Seq(fiveGroup)

    val selected = fiveGroup.filterNot(book => threeGroup.contains(book)).head
    val adjustedThreeGroup = threeGroup ++ Seq(selected)
    val adjustedFiveGroup = fiveGroup.filterNot(book => book == selected)
    Seq(adjustedThreeGroup, adjustedFiveGroup)
  }

  private def safeSize(sizeGroupedMap: Map[Int, Seq[Seq[Book]]], sizeKey: Int): Int = {
    if (sizeGroupedMap.contains(sizeKey)) {
      sizeGroupedMap(sizeKey).size
    } else {
      0
    }
  }

  private def safeGetAtIndex(sizeGroupedMap: Map[Int, Seq[Seq[Book]]], sizeKey: Int, index: Int): Seq[Book] = {
    if (sizeGroupedMap.contains(sizeKey) && (sizeGroupedMap(sizeKey).size > index)) {
      sizeGroupedMap(sizeKey)(index)
    } else {
      null
    }
  }

  def removeBook(book: Book, list: List[Book]) = list diff List(book)

  def calculateSubsetPrice(books: Seq[Book]): Double = {
    val basePrice = calculateBasePrice(books)

    val adjustedPrice =
      (books.distinct.size) match {
        case (5) => basePrice * .75
        case (4) => basePrice * .80
        case (3) => basePrice * .90
        case (2) => basePrice * .95
        case (_) => basePrice
      }
    
    adjustedPrice
  }

  private def calculateBasePrice(books: Seq[Book]): Double = {
    8.00 * books.size
  }
}
