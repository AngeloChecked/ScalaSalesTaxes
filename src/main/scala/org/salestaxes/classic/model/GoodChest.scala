package org.salestaxes.classic.model

import org.salestaxes.classic.model.good.Good

class GoodChest(val goods: List[Good]) {
  def totalPrice(): Double =
    goods.foldLeft(0.0)((total, good) => { good sumPrice total })
  def totalSalesTaxes(): Double =
    goods.foldLeft(0.0)((total, good) => { good sumTax total })
}
