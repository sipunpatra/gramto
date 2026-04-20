package com.orsac.gov.villagesathi.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.adapter.CategoryGroupAdapter
import com.orsac.gov.villagesathi.databinding.FragmentCategoriesBinding
import com.orsac.gov.villagesathi.ui.categories.repository.CategoriesRepository
import com.orsac.gov.villagesathi.ui.categories.viewmodel.CategoriesViewModel
import com.orsac.gov.villagesathi.ui.categories.viewmodel.CategoriesViewModelFactory
import com.orsac.gov.villagesathi.ui.products.ProductListFragment

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CategoriesRepository()
        val factory = CategoriesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[CategoriesViewModel::class.java]

        observeViewModel()
        viewModel.loadCategoryGroups()
    }

    private fun observeViewModel() {
        viewModel.categoryGroups.observe(viewLifecycleOwner) { categoryGroups ->
            binding.rvCategories.adapter = CategoryGroupAdapter(categoryGroups) { category ->
                // Navigate to ProductListFragment
                val fragment = ProductListFragment.newInstance(category.name)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
