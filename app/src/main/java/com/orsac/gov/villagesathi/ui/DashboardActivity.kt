package com.orsac.gov.villagesathi.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.databinding.ActivityDashboardBinding
import com.orsac.gov.villagesathi.ui.cart.CheckoutFragment
import com.orsac.gov.villagesathi.ui.categories.CategoriesFragment
import com.orsac.gov.villagesathi.ui.home.HomeFragment
import com.orsac.gov.villagesathi.ui.viewmodel.CartViewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
//            insets
//        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> selectedFragment = HomeFragment()
                R.id.navigation_categories -> selectedFragment = CategoriesFragment()
                R.id.navigation_order_again -> {
                    // Assuming you have an OrderAgainFragment or similar
                    // selectedFragment = OrderAgainFragment() 
                }
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }
            true
        }
    }
}
