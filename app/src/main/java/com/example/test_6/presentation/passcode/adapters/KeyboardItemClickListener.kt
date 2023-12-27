package com.example.test_6.presentation.passcode.adapters

interface KeyboardItemClickListener {
    fun onDigitClick(value: String)
    fun onDeleteClick()
    fun onFingerprintClick()
}