package classic


import org.salestaxes.classic.model.GoodChest
import org.salestaxes.classic.model.good.{Book, Food, Good, Medical}
import org.scalatest.FunSuite
import org.salestaxes.utils.RoundRules

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

  val goodChest1 = new GoodChest(items1)

  test("total 1") {
    assert(GoodChest.totalPrice(items1) == 42.32)
  }

  test("Sales Taxes total 1") {
    assert(GoodChest.totalSalesTaxes(items1) == 1.50)
  }

  test("chest total 1") {
    assert(goodChest1.totalPrice == 42.32)
    assert(goodChest1.totalSalesTaxes == 1.50)
  }

  val items2 = List(
    new Food("box of chocolates", 1, 10.00, true),
    new Good("bottle of perfume", 1, 47.50, true)
  )

  val goodChest2 = new GoodChest(items2)

  test("total 2") {
    assert(GoodChest.totalPrice(items2) == 65.15)
  }

  test("Sales Taxes total 2") {
    assert(GoodChest.totalSalesTaxes(items2) == 7.65)
  }

  test("chest total 2") {
    assert(goodChest2.totalPrice == 65.15)
    assert(goodChest2.totalSalesTaxes == 7.65)
  }

  val items3 = List(
    new Good("bottle of perfume", 1, 27.99, true),
    new Good("bottle of perfume", 1, 18.99, false),
    new Medical("packet of headache pills", 1, 9.75, false),
    new Food("box of chocolates", 3, 11.25, true)
  )

  val goodChest3 = new GoodChest(items3)

  test("total 3") {
    assert(GoodChest.totalPrice(items3) == 98.38)
  }

  test("Sales Taxes total 3") {
    assert(GoodChest.totalSalesTaxes(items3) == 7.90)
  }

  test("chest total 3") {
    assert(goodChest3.totalPrice == 98.38)
    assert(goodChest3.totalSalesTaxes == 7.90)
  }

}
