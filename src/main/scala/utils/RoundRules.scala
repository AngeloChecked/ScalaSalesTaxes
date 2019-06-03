package utils

import model._



object RoundRules {
  def roundUpNear05(value: Double): Double = (Math.ceil(value*20))/20
}