package utils

import model._

object Utils {

  def roundUpNear05(value: Double): Double = (Math.ceil(value*20))/20

  def goodsTotalPrice(goods: List[classic.Good]): Double =
    goods.foldLeft(0.0) ((a,b) => { a + b.price })

  def goodsSalesTaxesTotal(goods: List[classic.Good]): Double =
    goods.foldLeft(0.0) ((a,b) => { b sumTax a })

  //def goodsTotalPrice(goods: List[classic.Good]): Double = goods.reduce((a,b) => { a.price+b.price })


}

object Rounder {
  def roundUpNear05(value: Double): Double = (Math.ceil(value*20))/20
}