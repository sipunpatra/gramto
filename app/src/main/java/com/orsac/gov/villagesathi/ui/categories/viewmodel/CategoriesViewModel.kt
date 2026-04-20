package com.orsac.gov.villagesathi.ui.categories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orsac.gov.villagesathi.model.CategoryGroupModel
import com.orsac.gov.villagesathi.ui.categories.repository.CategoriesRepository

class CategoriesViewModel(private val repository: CategoriesRepository) : ViewModel() {

    private val _categoryGroups = MutableLiveData<List<CategoryGroupModel>>()
    val categoryGroups: LiveData<List<CategoryGroupModel>> = _categoryGroups

    fun loadCategoryGroups() {
        _categoryGroups.postValue(repository.getCategoryGroups())
    }
}
