package org.salestaxes.good

import org.scalatest.{Matchers, WordSpec}

class GoodSpec extends WordSpec with Matchers {

  "a price" when {
    "rounded up" should {
      "return the itself nearest .05 number" in {
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

  "imported and taxed generic goods string" should {
    "return '1 imported <name>: <price>'" in {
      val good = new Good("bottle of perfume", 1, 47.50, true)

      good.toString should be("1 imported bottle of perfume: 54.65")
    }

    "return '<quantity> imported <name>: <price>'" in {
      val good = new Good("bottle of perfume", 2, 47.50, true)

      good.toString should be("2 imported bottle of perfume: 109.30")
    }
  }

  "taxed generic goods string" should {
    "return '1 <name>: <price>'" in {
      val good = new Good("music CD", 1, 14.99, false)

      good.toString should be("1 music CD: 16.49")
    }

    "return '<quantity> <name>: <price>'" in {
      val good = new Good("music CD", 2, 14.99, false)

      good.toString should be("2 music CD: 32.98")
    }
  }

  "generic no-taxable goods string" should {
    "return '1 <name>: <price>'" in {
      val good = new Food("chocolate bar", 1, 0.85, false)

      good.toString should be("1 chocolate bar: 0.85")
    }
  }

  "generic imported no-taxable goods string" should {
    "return '<quantity> imported <name>: <price>'" in {
      val good = new Food("box of chocolates", 3, 11.25, true)

      good.toString should be("3 imported box of chocolates: 35.55")
    }
  }
}
