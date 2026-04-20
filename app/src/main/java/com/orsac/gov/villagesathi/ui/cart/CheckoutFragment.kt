package com.orsac.gov.villagesathi.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.orsac.gov.villagesathi.databinding.FragmentCheckoutBinding
import com.orsac.gov.villagesathi.ui.viewmodel.CartViewModel

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        observeCart()

        binding.btnProceedToPay.setOnClickListener {
            Toast.makeText(requireContext(), "Proceeding to Payment...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeCart() {
        cartViewModel.itemCount.observe(viewLifecycleOwner) { count ->
            binding.tvCheckoutItemCount.text = if (count == 1) "1 item" else "$count items"
        }

        cartViewModel.totalPrice.observe(viewLifecycleOwner) { total ->
            val formattedTotal = "₹${total.toInt()}"
            binding.tvBillItemTotal.text = formattedTotal
            binding.tvBillGrandTotal.text = formattedTotal
            binding.tvFinalTotal.text = formattedTotal
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
