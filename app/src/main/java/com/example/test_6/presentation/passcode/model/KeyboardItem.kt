package com.example.test_6.presentation.passcode.model

data class KeyboardItem(
    val value: String? = null,
    val image: Int? = null,
    val type: KeyboardType
)

enum class KeyboardType {
    DIGIT, DELETE, FINGERPRINT
}
