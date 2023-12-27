package com.example.test_6.domain

interface PasscodeRepository {
    suspend fun verifyPasscode(inputPasscode: String): Boolean
    fun getStoredPasscode(): Passcode
}