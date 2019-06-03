package org.salestaxes.sperimental

class GoodChest(val goods: List[Good]) {

  def totalPrice(): Double = GoodChest.totalPrice(goods)
  def totalSalesTaxes(): Double = GoodChest.totalSalesTaxes(goods)

  override def toString: String = super.toString

}

object GoodChest {
  def totalPrice(goods: List[Good]): Double =
    goods.foldLeft(0.0)((a, b) => { b sumPrice a })

  /* restituisce stesso valore dell gli elementi della lista */
//  def totalPrice2(goods: List[classic.Good]): Double =
//    goods.reduceLeft((a, b) => { a sumPrice b.price })

  def totalSalesTaxes(goods: List[Good]): Double =
    goods.foldLeft(0.0)((a, b) => { b sumTax a })
}
