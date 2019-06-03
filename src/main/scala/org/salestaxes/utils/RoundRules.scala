package org.salestaxes.utils

object RoundRules {
  def roundUpNear05(value: Double): Double = (Math.ceil(value*20))/20
}