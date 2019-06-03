package org.salestaxes.classic.model.good

import org.salestaxes.classic.model.parameter.{Importable, Roundable, Taxable}
import org.salestaxes.utils.RoundRules

class Good(val name: String,
           val quantity: Int,
           protected val _price: Double,
           val imported: Boolean,
           private val rouding: Roundable = new Roundable(
             RoundRules.roundUpNear05),
           private val taxing: Taxable = new Taxable(10),
           private val importing: Importable = new Importable(5)) {

  def price: Double = {
    val (taxAct, importTaxAct) = rounedTaxes
    (_price + taxAct + importTaxAct) * quantity
  }

  def sumTax(value: Double): Double = {
    val (taxAct, importTaxAct) = rounedTaxes
    val taxTotal = (taxAct + importTaxAct) * quantity
    rouding.round(taxTotal + value)
  }

  /*
  def sumTax(aGood: Good): Double = {

    val (taxAct, importTaxAct) = rounedTaxes()
    val taxTotal = (taxAct + importTaxAct) * quantity

    val (aTaxAct, aImportTaxAct) = aGood.rounedTaxes()
    val aTaxTotal = (aTaxAct + aImportTaxAct) * quantity

    rouding.round(taxTotal + aTaxTotal)
  }
   */

  def sumPrice(value: Double): Double = price + value

  def sumPrice(aGood: Good): Double = price + aGood.price

  def rounedTaxes: (Double, Double) = {
    val taxAct = rouding.round(taxing.calculateTax(_price))
    val importTaxAct =
      if (imported) rouding.round(importing.calculateImportTax(_price)) else 0.0
    (taxAct, importTaxAct)
  }

  override def toString = {
    val importString = if (imported) " imported " else " "
    s"$quantity$importString$name: %.2f".format(price)
  }

}
