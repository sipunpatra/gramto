package com.orsac.gov.villagesathi.ui.categories.repository

import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.model.CategoryGroupModel
import com.orsac.gov.villagesathi.model.CategoryModel

class CategoriesRepository {

    fun getCategoryGroups(): List<CategoryGroupModel> {
        return listOf(
            CategoryGroupModel("Fresh items", listOf(
                CategoryModel("Fresh Vegetables", R.drawable.ic_placeholder_image),
                CategoryModel("Fresh Fruits", R.drawable.ic_placeholder_image),
                CategoryModel("Dairy, Bread and Eggs", R.drawable.ic_placeholder_image),
                CategoryModel("Meat", R.drawable.ic_placeholder_image)
            )),
            CategoryGroupModel("Puja Items", listOf(
                CategoryModel("Diya & Lamps", R.drawable.ic_placeholder_image),
                CategoryModel("Agarbatti", R.drawable.ic_placeholder_image),
                CategoryModel("Camphor", R.drawable.ic_placeholder_image),
                CategoryModel("Coconut", R.drawable.ic_placeholder_image),
                CategoryModel("Flowers", R.drawable.ic_placeholder_image),
                CategoryModel("Puja Thali", R.drawable.ic_placeholder_image),
                CategoryModel("Incense Sticks", R.drawable.ic_placeholder_image),
                CategoryModel("Kumkum & Haldi", R.drawable.ic_placeholder_image)
            )),

            CategoryGroupModel("Festival Specials", listOf(
                CategoryModel("Durga Puja Items", R.drawable.ic_placeholder_image),
                CategoryModel("Lakshmi Puja Items", R.drawable.ic_placeholder_image),
                CategoryModel("Kali Puja Items", R.drawable.ic_placeholder_image),
                CategoryModel("Ganesh Puja Items", R.drawable.ic_placeholder_image),
                CategoryModel("Saraswati Puja Items", R.drawable.ic_placeholder_image),
                CategoryModel("Rath Yatra Specials", R.drawable.ic_placeholder_image)
            )),
            CategoryGroupModel("Grocery & Kitchen", listOf(
                CategoryModel("Atta, Rice and Dal", R.drawable.ic_placeholder_image),
                CategoryModel("Masalas", R.drawable.ic_placeholder_image),
                CategoryModel("Oils and Ghee", R.drawable.ic_placeholder_image),
                CategoryModel("Cereals and Breakfast", R.drawable.ic_placeholder_image)
            )),
            CategoryGroupModel("Snacks & drinks", listOf(
                CategoryModel("Snacks", R.drawable.ic_placeholder_image),
                CategoryModel("Ice cream", R.drawable.ic_placeholder_image),
                CategoryModel("Chocolates", R.drawable.ic_placeholder_image),
                 CategoryModel("Cold drinks", R.drawable.ic_placeholder_image)
            ))
        )
    }
}
