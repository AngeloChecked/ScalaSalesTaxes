package model.classic.model.parameter

class Taxable(percentage: Int = 10) {
  def calculateTax(price: Double): Double = price / 100 * percentage
}

class NoTaxable extends Taxable(0)