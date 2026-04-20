package com.orsac.gov.villagesathi.ui.products

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.orsac.gov.villagesathi.adapter.ProductAdapter
import com.orsac.gov.villagesathi.databinding.FragmentProductListBinding
import com.orsac.gov.villagesathi.ui.products.repository.ProductListRepository
import com.orsac.gov.villagesathi.ui.products.viewmodel.ProductListViewModel
import com.orsac.gov.villagesathi.ui.products.viewmodel.ProductListViewModelFactory
import com.orsac.gov.villagesathi.ui.viewmodel.CartViewModel

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductListViewModel
    private val cartViewModel: CartViewModel by activityViewModels()
    private var categoryName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryName = it.getString(ARG_CATEGORY_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCategoryTitle.text = categoryName
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val repository = ProductListRepository()
        val factory = ProductListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ProductListViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
        
        categoryName?.let { viewModel.loadProducts(it) }
    }

    private fun setupRecyclerView() {
        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            binding.rvProducts.adapter = ProductAdapter(
                products = products,
                onAddClick = { product ->
                    cartViewModel.addToCart(product)
                },
                onProductClick = { product ->
                    val intent = Intent(requireContext(), ProductDetailsActivity::class.java).apply {
                        putExtra("product_id", product.id)
                    }
                    startActivity(intent)
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_CATEGORY_NAME = "category_name"

        @JvmStatic
        fun newInstance(categoryName: String) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_NAME, categoryName)
                }
            }
    }
}
