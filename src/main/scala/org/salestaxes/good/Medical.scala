package org.salestaxes.good

class Medical(name: String, quantity: Int, price: Double, imported: Boolean)
    extends Good(name, quantity, price, imported, taxFrom = Good.noUsage)
