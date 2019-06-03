package model.classic

import utils.Utils

trait Importable {
  def calculateImportTax(price: Double): Double
}

trait Roundable {
  def round(price: Double): Double
}

trait Taxable {
  def calculateTax(price: Double): Double
}



class Roundable05 extends Roundable {
  def round(price: Double): Double = Utils.roundUpNear05(price)
}

class Importable5(percentage: Int = 5) extends Importable{
  def calculateImportTax(price: Double): Double = price/100*percentage
}

class Taxable10(percentage: Int = 10) extends Taxable {
  def calculateTax(price: Double): Double = price/100*percentage
}

class NoTaxable extends Taxable {
  def calculateTax(price: Double): Double = 0
}

class Good (val name: String,
            var quantity: Int,
            protected var _price: Double,
            var imported: Boolean,
            private var rouding: Roundable = new Roundable05,
            private var taxing: Taxable = new Taxable10,
            private var importing: Importable = new Importable5) {


  def price: Double = {
    val (taxAct, importTaxAct) = rounedTaxes()
    (_price + taxAct + importTaxAct) * quantity
  }

  def price_=(aPrice: Double) { _price = aPrice }

  def sumTax(value: Double): Double = {
    val (taxAct, importTaxAct) = rounedTaxes()
    val taxTotal = (taxAct + importTaxAct) * quantity
    rouding.round( taxTotal + value)
  }

  private def rounedTaxes():(Double, Double) = {
    val taxAct = rouding.round(taxing.calculateTax(_price))
    val importTaxAct = if (imported) rouding.round(importing.calculateImportTax(_price)) else 0.0
    (taxAct, importTaxAct)
  }

  override def toString = {
    val importString = if (imported) "imported" else ""
    s"$quantity $importString $name: %.2f".format(price)
  }

}

class Book(name: String,
           quantity: Int,
           price: Double,
           imported: Boolean)
  extends Good(name,quantity,price,imported, taxer = new NoTaxable)

class Food(name: String,
           quantity: Int,
           price: Double,
           imported: Boolean)
  extends Good(name,quantity,price,imported, taxer = new NoTaxable)

class Medical(name: String,
              quantity: Int,
              price: Double,
              imported: Boolean)
  extends Good(name,quantity,price,imported, taxer = new NoTaxable)


