package org.salestaxes

import org.salestaxes.good.{Book, Food, Good, Medical}
import org.scalatest.{Matchers, WordSpec}

class GoodChestSpec extends WordSpec with Matchers {

  "the mixed chest 1" should {
    val items = List(
      new Book("book", 2, 12.49, false),
      new Good("music CD", 1, 14.99, false),
      new Food("chocolate bar", 1, 0.85, false)
    )

    val (totalPrice, totalSalesTaxes) = totalsOf(items)

    "return total price" in {
      totalPrice should be(42.32)
    }

    "return total salex taxes" in {
      totalSalesTaxes should be(1.50)
    }
  }

  "the mixed chest 2" should {
    val items = List(
      new Food("box of chocolates", 1, 10.00, true),
      new Good("bottle of perfume", 1, 47.50, true)
    )

    val (totalPrice, totalSalesTaxes) = totalsOf(items)

    "return total price" in {
      totalPrice should be(65.15)
    }

    "return total sales taxes" in {
      totalSalesTaxes should be(7.65)
    }
  }

  "the mixed chest 3" should {
    val items = List(
      new Good("bottle of perfume", 1, 27.99, true),
      new Good("bottle of perfume", 1, 18.99, false),
      new Medical("packet of headache pills", 1, 9.75, false),
      new Food("box of chocolates", 3, 11.25, true)
    )

    val (totalPrice, totalSalesTaxes) = totalsOf(items)

    "return total price" in {
      totalPrice should be(98.38)
    }
    "return total sales taxes" in {
      totalSalesTaxes should be(7.90)
    }
  }

  private def totalsOf(goods: List[Good]) = {
    val chest = new GoodChest(goods)
    (chest.totalPrice, chest.totalSalesTaxes)
  }
}
