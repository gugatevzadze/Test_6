package com.example.test_6.presentation.passcode.data

import com.example.test_6.R

object LocalData {
    val keyboard = listOf<KeyboardItem>(
        KeyboardItem("1", null, KeyboardType.DIGIT),
        KeyboardItem("2", null, KeyboardType.DIGIT),
        KeyboardItem("3", null, KeyboardType.DIGIT),
        KeyboardItem("4", null, KeyboardType.DIGIT),
        KeyboardItem("5", null, KeyboardType.DIGIT),
        KeyboardItem("6", null, KeyboardType.DIGIT),
        KeyboardItem("7", null, KeyboardType.DIGIT),
        KeyboardItem("8", null, KeyboardType.DIGIT),
        KeyboardItem("9", null, KeyboardType.DIGIT),
        KeyboardItem("", R.drawable.ic_fingerprint, KeyboardType.FINGERPRINT),
        KeyboardItem("0", null,  KeyboardType.DIGIT),
        KeyboardItem("", R.drawable.ic_backspace,  KeyboardType.DELETE)
    )
}