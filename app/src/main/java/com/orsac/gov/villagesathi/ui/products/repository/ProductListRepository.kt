package com.orsac.gov.villagesathi.ui.products.repository

import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.model.ProductModel

class ProductListRepository {

    fun getProductsByCategory(categoryName: String): List<ProductModel> {
        return when (categoryName) {
            "Fresh Vegetables" -> listOf(
                ProductModel("1", "Fresh Spinach", "500 g", "₹40", "₹50", "20% OFF", R.drawable.ic_placeholder_image),
                ProductModel("2", "Red Onion", "1 kg", "₹30", "₹40", "25% OFF", R.drawable.ic_placeholder_image),
                ProductModel("3", "Potato", "1 kg", "₹25", "₹30", "16% OFF", R.drawable.ic_placeholder_image),
                ProductModel("4", "Tomato", "500 g", "₹20", "₹25", "20% OFF", R.drawable.ic_placeholder_image)
            )
            "Fresh Fruits" -> listOf(
                ProductModel("5", "Apple", "1 kg", "₹150", "₹180", "15% OFF", R.drawable.ic_placeholder_image),
                ProductModel("6", "Banana", "1 doz", "₹60", "₹70", "14% OFF", R.drawable.ic_placeholder_image),
                ProductModel("7", "Orange", "1 kg", "₹80", "₹100", "20% OFF", R.drawable.ic_placeholder_image)
            )
            "Dairy, Bread and Eggs" -> listOf(
                ProductModel("8", "Milk", "500 ml", "₹28", "₹30", "5% OFF", R.drawable.ic_placeholder_image),
                ProductModel("9", "Brown Bread", "400 g", "₹45", "₹50", "10% OFF", R.drawable.ic_placeholder_image),
                ProductModel("10", "Eggs", "6 pcs", "₹42", "₹48", "12% OFF", R.drawable.ic_placeholder_image)
            )
            "Meat" -> listOf(
                ProductModel("26", "Fresh Chicken", "1 kg", "₹220", "₹250", "12% OFF", R.drawable.ic_placeholder_image),
                ProductModel("27", "Mutton", "1 kg", "₹700", "₹750", "6% OFF", R.drawable.ic_placeholder_image),
                ProductModel("28", "Fish (Rohu)", "1 kg", "₹180", "₹200", "10% OFF", R.drawable.ic_placeholder_image)
            )
            "Atta, Rice and Dal" -> listOf(
                ProductModel("11", "Chakki Atta", "5 kg", "₹210", "₹250", "16% OFF", R.drawable.ic_placeholder_image),
                ProductModel("12", "Basmati Rice", "1 kg", "₹120", "₹150", "20% OFF", R.drawable.ic_placeholder_image),
                ProductModel("13", "Arhar Dal", "1 kg", "₹140", "₹160", "12% OFF", R.drawable.ic_placeholder_image)
            )
            "Diya & Lamps" -> listOf(
                ProductModel("16", "Clay Diya", "12 pcs", "₹50", "₹60", "16% OFF", R.drawable.ic_placeholder_image),
                ProductModel("17", "Brass Diya", "1 pc", "₹150", "₹200", "25% OFF", R.drawable.ic_placeholder_image),
                ProductModel("18", "Cotton Wicks", "50 pcs", "₹20", "₹25", "20% OFF", R.drawable.ic_placeholder_image)
            )
            "Agarbatti", "Incense Sticks" -> listOf(
                ProductModel("19", "Sandalwood Incense", "1 pack", "₹45", "₹50", "10% OFF", R.drawable.ic_placeholder_image),
                ProductModel("20", "Rose Incense", "1 pack", "₹40", "₹45", "11% OFF", R.drawable.ic_placeholder_image)
            )
            "Kumkum & Haldi" -> listOf(
                ProductModel("21", "Red Kumkum", "50 g", "₹15", "₹20", "25% OFF", R.drawable.ic_placeholder_image),
                ProductModel("22", "Organic Haldi", "100 g", "₹35", "₹40", "12% OFF", R.drawable.ic_placeholder_image)
            )
            "Durga Puja Items", "Lakshmi Puja Items", "Ganesh Puja Items" -> listOf(
                ProductModel("23", "Puja Samagri Kit", "1 kit", "₹499", "₹599", "16% OFF", R.drawable.ic_placeholder_image),
                ProductModel("24", "Yellow Cloth", "1 m", "₹60", "₹80", "25% OFF", R.drawable.ic_placeholder_image),
                ProductModel("25", "Holy Water (Ganga Jal)", "500 ml", "₹30", "₹35", "14% OFF", R.drawable.ic_placeholder_image)
            )
            "Snacks & drinks", "Snacks", "Cold drinks" -> listOf(
                ProductModel("14", "Potato Chips", "50 g", "₹20", "₹20", "0% OFF", R.drawable.ic_placeholder_image),
                ProductModel("15", "Coca Cola", "500 ml", "₹40", "₹40", "0% OFF", R.drawable.ic_placeholder_image)
            )
            else -> listOf(
                ProductModel("0", "Sample Product", "1 unit", "₹99", "₹120", "15% OFF", R.drawable.ic_placeholder_image)
            )
        }
    }
}
