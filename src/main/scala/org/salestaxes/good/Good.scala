package org.salestaxes.good

class Good(val name: String,
           val quantity: Int,
           val unitaryPrice: Double,
           val imported: Boolean,
           private val round: PriceModifier = Good.roundUpNear05,
           private val taxFrom: PriceModifier = Good.fractionize(10),
           private val importingTaxFrom: PriceModifier = Good.fractionize(5)) {

  def price: Double = (unitaryPrice + tax + importedTax) * quantity

  def sumTax(value: Double): Double =
    round((tax + importedTax) * quantity + value)

  def sumPrice(value: Double): Double = price + value

  def tax: Double = round(taxFrom(unitaryPrice))

  def importedTax: Double =
    if (imported) round(importingTaxFrom(unitaryPrice)) else 0.0

  override def toString: String = {
    val importString = if (imported) " imported " else " "
    s"$quantity$importString$name: %.2f".format(price)
  }
}

object Good {
  def fractionize(percentage: Int): PriceModifier =
    (price) => price / 100 * percentage

  def roundUpNear05: PriceModifier = (price) => (Math.ceil(price * 20)) / 20

  def noUsage: PriceModifier = ((_) => 0.0)
}
