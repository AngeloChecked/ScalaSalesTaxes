package model.classic.model.parameter

class Roundable(roundRule: Double => Double) {
  def round(price: Double): Double = roundRule(price)
}