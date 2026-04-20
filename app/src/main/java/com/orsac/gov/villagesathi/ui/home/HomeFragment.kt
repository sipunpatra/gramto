package com.orsac.gov.villagesathi.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.orsac.gov.villagesathi.R
import com.orsac.gov.villagesathi.adapter.BannerAdapter
import com.orsac.gov.villagesathi.adapter.BestsellerAdapter
import com.orsac.gov.villagesathi.adapter.CategoryAdapter
import com.orsac.gov.villagesathi.databinding.FragmentHomeBinding
import com.orsac.gov.villagesathi.ui.products.ProductListFragment
import com.orsac.gov.villagesathi.ui.repository.DashboardRepository
import com.orsac.gov.villagesathi.ui.viewmodel.DashboardViewModel
import com.orsac.gov.villagesathi.ui.viewmodel.DashboardViewModelFactory
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DashboardViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val handler = Handler(Looper.getMainLooper())
    private var hintIndex = 0
    private val hints = listOf(
        "Search \"Fresh Vegetables\"",
        "Search \"Milk and Bread\"",
        "Search \"Chicken and Fish\"",
        "Search \"Puja Samagri\"",
        "Search \"Snacks and Drinks\""
    )

    private val hintRunnable = object : Runnable {
        override fun run() {
            binding.etSearch.hint = hints[hintIndex]
            hintIndex = (hintIndex + 1) % hints.size
            handler.postDelayed(this, 3000)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            fetchCurrentLocation()
        } else {
            Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val repository = DashboardRepository()
        val factory = DashboardViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        setupRecyclerViews()
        observeViewModel()

        binding.location.setOnClickListener {
            showLocationBottomSheet()
        }

        viewModel.loadDashboardData()
        startHintScrolling()
    }

    private fun startHintScrolling() {
        handler.post(hintRunnable)
    }

    private fun showLocationBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.layout_location_bottom_sheet, null)
        bottomSheetDialog.setContentView(view)

        val btnFetchLocation = view.findViewById<LinearLayout>(R.id.btnFetchLocation)
        btnFetchLocation.setOnClickListener {
            bottomSheetDialog.dismiss()
            checkLocationPermissions()
        }

        bottomSheetDialog.show()
    }

    private fun checkLocationPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fetchCurrentLocation()
            }
            else -> {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun fetchCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val address = getAddressFromLocation(it.latitude, it.longitude)
                binding.location.text = address
            } ?: run {
                Toast.makeText(requireContext(), "Could not fetch location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAddressFromLocation(lat: Double, lng: Double): String {
        return try {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lng, 1)
            if (addresses?.isNotEmpty() == true) {
                val address = addresses[0]
                val city = address.locality ?: ""
                val subLocality = address.subLocality ?: ""
                if (subLocality.isNotEmpty()) "$subLocality, $city" else city
            } else {
                "Location found"
            }
        } catch (e: Exception) {
            "Location found"
        }
    }

    private fun setupRecyclerViews() {
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBanners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBestsellers.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            binding.rvCategories.adapter = CategoryAdapter(categories) { category ->
                val fragment = ProductListFragment.newInstance(category.name)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        viewModel.banners.observe(viewLifecycleOwner) { banners ->
            binding.rvBanners.adapter = BannerAdapter(banners)
        }

        viewModel.bestsellers.observe(viewLifecycleOwner) { bestsellers ->
            binding.rvBestsellers.adapter = BestsellerAdapter(bestsellers)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(hintRunnable)
        _binding = null
    }
}
