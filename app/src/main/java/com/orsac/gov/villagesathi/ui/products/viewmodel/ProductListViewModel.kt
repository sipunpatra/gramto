package com.orsac.gov.villagesathi.ui.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orsac.gov.villagesathi.model.ProductModel
import com.orsac.gov.villagesathi.ui.products.repository.ProductListRepository

class ProductListViewModel(private val repository: ProductListRepository) : ViewModel() {

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    fun loadProducts(categoryName: String) {
        _products.postValue(repository.getProductsByCategory(categoryName))
    }
}
