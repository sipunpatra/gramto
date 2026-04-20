package com.orsac.gov.villagesathi.ui.repository

import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.model.BannerModel
import com.orsac.gov.villagesathi.model.BestsellerModel
import com.orsac.gov.villagesathi.model.CategoryModel

class DashboardRepository {

    fun getCategories(): List<CategoryModel> {
        return listOf(
            CategoryModel("All", R.drawable.ic_placeholder_image),
            CategoryModel("Grocery", R.drawable.ic_placeholder_image),
            CategoryModel("Puja Items", R.drawable.ic_placeholder_image),
            CategoryModel("Meat & Fish", R.drawable.ic_placeholder_image),
            CategoryModel("Vegetable", R.drawable.ic_placeholder_image)
        )
    }

    fun getBanners(): List<BannerModel> {
        return listOf(
            BannerModel(R.drawable.ic_placeholder_image),
            BannerModel(R.drawable.ic_placeholder_image),
            BannerModel(R.drawable.ic_placeholder_image)
        )
    }

    fun getBestsellers(): List<BestsellerModel> {
        return listOf(
            BestsellerModel("Drinks & Juices", "+85 more", R.drawable.ic_placeholder_image),
            BestsellerModel("Chips & Namkeen", "+270 more", R.drawable.ic_placeholder_image),
            BestsellerModel("Ice Creams & More", "+34 more", R.drawable.ic_placeholder_image),
            BestsellerModel("Sweets & Bakery", "+43 more", R.drawable.ic_placeholder_image),
            BestsellerModel("Vegetables & Fruits", "+104 more", R.drawable.ic_placeholder_image)
        )
    }
}
