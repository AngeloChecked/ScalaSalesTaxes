package org.salestaxes

import org.salestaxes.good.{Book, Food, Good, Medical}
import org.scalatest.{Matchers, WordSpec}

class GoodChestSpec extends WordSpec with Matchers {

  "chest totals" should {
    "chest 1" in {
      val items = List(
        new Book("book", 2, 12.49, false),
        new Good("music CD", 1, 14.99, false),
        new Food("chocolate bar", 1, 0.85, false)
      )

      val (totalPrice, totalSalesTaxes) = totals(items)
      totalPrice should be(42.32)
      totalSalesTaxes should be(1.50)
    }

    "chest 2" in {
      val items = List(
        new Food("box of chocolates", 1, 10.00, true),
        new Good("bottle of perfume", 1, 47.50, true)
      )

      assertTotals(items, 65.15, 7.65)
    }

    "chest 3" in {
      val items = List(
        new Good("bottle of perfume", 1, 27.99, true),
        new Good("bottle of perfume", 1, 18.99, false),
        new Medical("packet of headache pills", 1, 9.75, false),
        new Food("box of chocolates", 3, 11.25, true)
      )

      totalsOf(items) {
        case (totalPrice, totalSalesTaxes) =>
          totalPrice should be(98.38)
          totalSalesTaxes should be(7.90)
      }
    }
  }

  private def assertTotals(goods: List[Good],
                           totalPrice: Double,
                           totalSalesTaxes: Double) = {
    val chest = new GoodChest(goods)
    chest.totalPrice should be(totalPrice)
    chest.totalSalesTaxes should be(totalSalesTaxes)
  }

  private def totalsOf(goods: List[Good])(f: (Double, Double) => Unit) = {
    val chest = new GoodChest(goods)
    f(chest.totalPrice, chest.totalSalesTaxes)
  }

  private def totals(goods: List[Good]) = {
    val chest = new GoodChest(goods)
    (chest.totalPrice, chest.totalSalesTaxes)
  }
}
