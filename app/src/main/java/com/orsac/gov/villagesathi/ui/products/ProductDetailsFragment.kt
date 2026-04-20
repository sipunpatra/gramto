package com.orsac.gov.villagesathi.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.databinding.FragmentProductDetailsBinding
import com.orsac.gov.villagesathi.model.ProductModel
import com.orsac.gov.villagesathi.ui.viewmodel.CartViewModel

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()
    private var product: ProductModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // In a real app, you'd pass the product ID and fetch details
        // For now, we'll assume the product object is passed (simplified)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mocking data for the UI based on the image
        binding.tvProductDetailName.text = "Cadbury Dairy Milk Crispello"
        binding.tvDetailPrice.text = "₹108 MRP ₹120"
        binding.tvDetailUnit.text = "3 x 35 g"
        binding.ivProductLarge.setImageResource(R.drawable.ic_placeholder_image)

        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

//        binding.btnAddToCart.setOnClickListener {
//            // product?.let { cartViewModel.addToCart(it) }
//            // For now, just a Toast or simple logic
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(productId: String) = ProductDetailsFragment().apply {
            arguments = Bundle().apply {
                putString("product_id", productId)
            }
        }
    }
}
