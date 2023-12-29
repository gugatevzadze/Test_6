package com.example.test_6.presentation.passcode.common

sealed class PasscodeResult {
    data class Success(val message: String) : PasscodeResult()
    data class Error(val message: String) : PasscodeResult()
}
