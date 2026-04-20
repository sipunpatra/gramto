package com.orsac.gov.villagesathi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orsac.gov.villagesathi.model.CartItem
import com.orsac.gov.villagesathi.model.ProductModel

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> get() = _totalPrice

    private val _itemCount = MutableLiveData<Int>(0)
    val itemCount: LiveData<Int> get() = _itemCount

    fun addToCart(product: ProductModel) {
        val currentItems = _cartItems.value ?: mutableListOf()
        val existingItem = currentItems.find { it.product.id == product.id }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            currentItems.add(CartItem(product))
        }

        _cartItems.value = currentItems
        calculateTotals()
    }

    private fun calculateTotals() {
        val currentItems = _cartItems.value ?: return
        var total = 0.0
        var count = 0
        for (item in currentItems) {
            val price = item.product.price.replace("₹", "").trim().toDoubleOrNull() ?: 0.0
            total += price * item.quantity
            count += item.quantity
        }
        _totalPrice.value = total
        _itemCount.value = count
    }
}