package model.classic.model.parameter

class Importable(percentage: Int = 5) {
  def calculateImportTax(price: Double): Double = price / 100 * percentage
}