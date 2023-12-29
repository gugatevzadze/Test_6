package com.example.test_6.presentation.passcode.common

interface KeyboardItemClickListener {
    fun onDigitClick(value: String)
    fun onDeleteClick()
    fun onFingerprintClick()
}