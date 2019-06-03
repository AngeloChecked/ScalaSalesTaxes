package org.salestaxes.classic.model.parameter

class Taxable(percentage: Int = 10) {
  def calculateTax(price: Double): Double = price / 100 * percentage
}

object Taxable {
  def noTaxable = new Taxable(0)
}

class NoTaxable extends Taxable(0)