package model.classic.model.good

import model.classic.model.parameter.NoTaxable

class Book(name: String, quantity: Int, price: Double, imported: Boolean)
    extends Good(name, quantity, price, imported, taxing = new NoTaxable)
