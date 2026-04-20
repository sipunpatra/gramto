package com.orsac.gov.villagesathi.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orsac.gov.villagesathi.user.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(otp: String) {
        val result = repository.verifyOtp(otp)
        _loginResult.postValue(result)
    }
}
