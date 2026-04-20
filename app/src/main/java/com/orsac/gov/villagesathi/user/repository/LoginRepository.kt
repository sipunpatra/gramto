package com.orsac.gov.villagesathi.user.repository

class LoginRepository {
    fun verifyOtp(otp: String): Boolean {
        // In a real app, you would have a remote or local data source to verify the OTP.
        // For now, we'll just hardcode it.
        return otp == "123456"
    }
}
