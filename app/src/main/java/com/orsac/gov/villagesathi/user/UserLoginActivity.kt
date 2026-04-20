package com.orsac.gov.villagesathi.user

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.orsac.gov.villagesathi.databinding.ActivityUserLoginBinding
import com.orsac.gov.villagesathi.ui.DashboardActivity
import com.orsac.gov.villagesathi.user.repository.LoginRepository
import com.orsac.gov.villagesathi.user.viewmodel.LoginViewModel
import com.orsac.gov.villagesathi.user.viewmodel.LoginViewModelFactory

class UserLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var phoneNumberHintLauncher: ActivityResultLauncher<IntentSenderRequest>
    private lateinit var viewModel: LoginViewModel

    private val otpEditTexts by lazy {
        listOf(
            binding.etOtp1,
            binding.etOtp2,
            binding.etOtp3,
            binding.etOtp4,
            binding.etOtp5,
            binding.etOtp6
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = LoginRepository()
        val factory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        // Initially hide OTP layout and login button
        binding.otpLayout.visibility = View.GONE
        binding.btnLogin.visibility = View.GONE

        binding.btnSendOtp.setOnClickListener {
            binding.tvLoginTitle.text = "Enter OTP"
            binding.tilMobileNumber.visibility = View.GONE
            binding.btnSendOtp.visibility = View.GONE
            binding.tvRegister.visibility = View.GONE

            binding.otpLayout.visibility = View.VISIBLE
            binding.btnLogin.visibility = View.VISIBLE
            otpEditTexts[0].requestFocus()
        }

        binding.btnLogin.setOnClickListener {
            val enteredOtp = otpEditTexts.joinToString("") { it.text.toString() }
            viewModel.login(enteredOtp)
        }

        viewModel.loginResult.observe(this) { isSuccess ->
            if (isSuccess) {
                // Save login status
                val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putBoolean("is_logged_in", true)
                    apply()
                }

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        setupOtpEditTexts()

        phoneNumberHintLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                try {
                    val phoneNumber = Identity.getSignInClient(this).getPhoneNumberFromIntent(it.data)
                    binding.etMobileNumber.setText(phoneNumber.replace("+91", ""))
                } catch (e: Exception) {
                    Log.e("UserLoginActivity", "Error getting phone number from hint", e)
                }
            }
        }

        requestPhoneNumberHint()
    }

    private fun requestPhoneNumberHint() {
        val request = GetPhoneNumberHintIntentRequest.builder().build()
        Identity.getSignInClient(this)
            .getPhoneNumberHintIntent(request)
            .addOnSuccessListener {
                phoneNumberHintLauncher.launch(IntentSenderRequest.Builder(it.intentSender).build())
            }
            .addOnFailureListener {
                // Handle failure
                Log.e("UserLoginActivity", "Phone number hint failed", it)
            }
    }

    private fun setupOtpEditTexts() {
        for (i in otpEditTexts.indices) {
            otpEditTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpEditTexts.size - 1) {
                        otpEditTexts[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            otpEditTexts[i].setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && otpEditTexts[i].text.isEmpty() && i > 0) {
                    otpEditTexts[i - 1].requestFocus()
                }
                false
            }
        }
    }
}
