package classic

import org.salestaxes.classic.model.GoodChest
import org.salestaxes.classic.model.good.{Book, Food, Good, Medical}
import org.scalatest.{FunSuite, Matchers, WordSpec}

class GoodTest extends WordSpec with Matchers {

  "a price" when {
    "round up" should {
      "nearest .05" in {
        Good.roundUpNear05(.01) should be(.05)
        Good.roundUpNear05(.02) should be(.05)
        Good.roundUpNear05(.03) should be(.05)
        Good.roundUpNear05(.04) should be(.05)
        Good.roundUpNear05(.05) should be(.05)

        Good.roundUpNear05(.06) should be(.1)
        Good.roundUpNear05(.07) should be(.1)
        Good.roundUpNear05(.08) should be(.1)
        Good.roundUpNear05(.09) should be(.1)
        Good.roundUpNear05(.1) should be(.1)
      }
    }
  }


  "good strings" should {
    "1 item imported and taxed" in {
      val good = new Good("bottle of perfume", 1, 47.50, true)

      good.toString should be("1 imported bottle of perfume: 54.65")
    }

    "2 items imported and taxed" in {
      val good = new Good("bottle of perfume", 2, 47.50, true)

      good.toString should be("2 imported bottle of perfume: 109.30")
    }

    "1 item taxed" in {
      val good = new Good("music CD", 1, 14.99, false)

      good.toString should be("1 music CD: 16.49")
    }

    "2 items taxed" in {
      val good = new Good("music CD", 2, 14.99, false)

      good.toString should be("2 music CD: 32.98")
    }

    "1 food" in {
      val good = new Food("chocolate bar", 1, 0.85, false)

      good.toString should be("1 chocolate bar: 0.85")
    }

    "3 food imported" in {
      val good = new Food("box of chocolates", 3, 11.25, true)

      good.toString should be("3 imported box of chocolates: 35.55")
    }
  }


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
