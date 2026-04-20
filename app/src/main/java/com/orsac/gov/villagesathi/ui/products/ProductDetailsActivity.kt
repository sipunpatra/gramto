package com.orsac.gov.villagesathi.ui.products

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.databinding.FragmentProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
//            insets
//        }

        // Mocking data for the UI based on the image
        binding.tvProductDetailName.text = "Cadbury Dairy Milk Crispello"
        binding.tvDetailPrice.text = "₹108 MRP ₹120"
        binding.tvDetailUnit.text = "3 x 35 g"
        binding.ivProductLarge.setImageResource(R.drawable.ic_placeholder_image)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnAddToCart.setOnClickListener {
            // Add to cart logic
        }
    }
}
