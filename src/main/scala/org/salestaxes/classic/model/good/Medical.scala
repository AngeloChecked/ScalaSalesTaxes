package org.salestaxes.classic.model.good

import org.salestaxes.classic.model.parameter.NoTaxable

class Medical(name: String, quantity: Int, price: Double, imported: Boolean)
    extends Good(name, quantity, price, imported, taxing = new NoTaxable)
