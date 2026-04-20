package com.orsac.gov.villagesathi.model

data class CartItem(
    val product: ProductModel,
    var quantity: Int = 1
)