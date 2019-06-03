package model

import utils.Utils

trait Importable {
  var imported: Boolean
  protected var _price: Double

  def importTax(): Double
}

trait Roundable {
  protected def round(price: Double): Double
}

trait Taxable {
  protected var _price: Double
  def tax(): Double
}



trait Roundable05 extends Roundable {
   protected def round(price: Double): Double = Utils.roundUpNear05(price)
}

trait Importable5 extends Importable with Roundable05 {
  override def importTax: Double = if (imported) round(_price/100*5) else 0.0
}

trait Taxable10 extends Taxable with Roundable05 {
  override def tax: Double = round(_price/100*10)
}

trait NoTaxable extends Taxable {
  override def tax: Double = 0
}



class Good (val name: String,
            var quantity: Int,
            protected var _price: Double,
            var imported: Boolean)
  extends Taxable10 with Importable5 {

  def price: Double = (_price + importTax + tax ) * quantity
  def price_=(aPrice: Double) { _price = aPrice }

  def sumTax(value: Double): Double = round(((importTax+tax) * quantity) + (value))

  override def toString = s"$quantity ${if (imported) "imported " else ""}$name: %.2f".format(price)
  //override def toString = s"$quantity ${if (imported) "imported " else ""}$name at %.2f".format(price)
}

class Book(name: String,
           quantity: Int,
           price: Double,
           imported: Boolean)
  extends Good(name,quantity,price,imported) with NoTaxable

class Food(name: String,
           quantity: Int,
           price: Double,
           imported: Boolean)
  extends Good(name,quantity,price,imported) with NoTaxable

class Medical(name: String,
           quantity: Int,
           price: Double,
           imported: Boolean)
  extends Good(name,quantity,price,imported) with NoTaxable


