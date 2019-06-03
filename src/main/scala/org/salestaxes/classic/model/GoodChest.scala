package org.salestaxes.classic.model

import org.salestaxes.classic.model.good.Good

class GoodChest(val goods: List[Good]) {

  def totalPrice(): Double = GoodChest.totalPrice(goods)
  def totalSalesTaxes(): Double = GoodChest.totalSalesTaxes(goods)

  override def toString: String = super.toString

}

object GoodChest {
  def totalPrice(goods: List[Good]): Double =
    goods.foldLeft(0.0)((a, b) => { b sumPrice a })

  def totalSalesTaxes(goods: List[Good]): Double =
    goods.foldLeft(0.0)((a, b) => { b sumTax a })
}
