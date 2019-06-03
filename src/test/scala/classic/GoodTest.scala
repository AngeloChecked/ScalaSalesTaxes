package classic

import model.classic._
import org.scalatest.FunSuite
import utils.RoundRules

class GoodTest extends FunSuite {

  test("Round up nearest .05") {

    assert(RoundRules.roundUpNear05(.01) == .05)
    assert(RoundRules.roundUpNear05(.02) == .05)
    assert(RoundRules.roundUpNear05(.03) == .05)
    assert(RoundRules.roundUpNear05(.04) == .05)
    assert(RoundRules.roundUpNear05(.05) == .05)

    assert(RoundRules.roundUpNear05(.06) == .1)
    assert(RoundRules.roundUpNear05(.07) == .1)
    assert(RoundRules.roundUpNear05(.08) == .1)
    assert(RoundRules.roundUpNear05(.09) == .1)
    assert(RoundRules.roundUpNear05(.1) == .1)
  }

  test("1 item imported and taxed") {
    val good = new Good("bottle of perfume", 1, 47.50, true)

    assert(good.toString == "1 imported bottle of perfume: 54.65")
  }

  test("2 items imported and taxed") {
    val good = new Good("bottle of perfume", 2, 47.50, true)

    assert(good.toString == "2 imported bottle of perfume: 109.30")
  }

  test("1 item taxed") {
    val good = new Good("music CD", 1, 14.99, false)

    assert(good.toString == "1 music CD: 16.49")
  }

  test("2 items taxed") {
    val good = new Good("music CD", 2, 14.99, false)

    assert(good.toString == "2 music CD: 32.98")
  }

  test("1 food") {
    val good = new Food("chocolate bar", 1, 0.85, false)

    assert(good.toString == "1 chocolate bar: 0.85")
  }

  test("3 food imported") {
    val good = new Food("box of chocolates", 3, 11.25, true)

    assert(good.toString == "3 imported box of chocolates: 35.55")
  }

  val items1 = List(
    new Book("book", 2, 12.49, false),
    new Good("music CD", 1, 14.99, false),
    new Food("chocolate bar", 1, 0.85, false)
  )

  test("total 1") {
    assert(GoodChest.totalPrice(items1) == 42.32)
  }

  test("Sales Taxes total 1") {
    assert(GoodChest.totalSalesTaxes(items1) == 1.50)
  }

  val items2 = List(
    new Food("box of chocolates", 1, 10.00, true),
    new Good("bottle of perfume", 1, 47.50, true)
  )

  test("total 2") {
    assert(GoodChest.totalPrice(items3) == 98.38)
  }

  test("Sales Taxes total 2") {
    assert(GoodChest.totalSalesTaxes(items3) == 7.90)
  }

  val items3 = List(
    new Good("bottle of perfume", 1, 27.99, true),
    new Good("bottle of perfume", 1, 18.99, false),
    new Medical("packet of headache pills", 1, 9.75, false),
    new Food("box of chocolates", 3, 11.25, true)
  )

  test("total 3") {
    assert(GoodChest.totalPrice(items3) == 98.38)
  }

  test("Sales Taxes total 3") {
    assert(GoodChest.totalSalesTaxes(items3) == 7.90)
  }

}
