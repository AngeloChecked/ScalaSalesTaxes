package org.salestaxes

import org.salestaxes.good.Good

class GoodChest(val goods: List[Good]) {
  def totalPrice(): Double =
    goods.foldLeft(0.0)((total, good) => { good sumPrice total })
  def totalSalesTaxes(): Double =
    goods.foldLeft(0.0)((total, good) => { good sumTax total })
}
