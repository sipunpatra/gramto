package com.orsac.gov.villagesathi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orsac.gov.villagesathi.model.BannerModel
import com.orsac.gov.villagesathi.model.BestsellerModel
import com.orsac.gov.villagesathi.model.CategoryModel
import com.orsac.gov.villagesathi.ui.repository.DashboardRepository

class DashboardViewModel(private val repository: DashboardRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    private val _banners = MutableLiveData<List<BannerModel>>()
    val banners: LiveData<List<BannerModel>> = _banners

    private val _bestsellers = MutableLiveData<List<BestsellerModel>>()
    val bestsellers: LiveData<List<BestsellerModel>> = _bestsellers

    fun loadDashboardData() {
        _categories.postValue(repository.getCategories())
        _banners.postValue(repository.getBanners())
        _bestsellers.postValue(repository.getBestsellers())
    }
}
