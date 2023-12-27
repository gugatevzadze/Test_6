package com.example.test_6.presentation.passcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_6.domain.PasscodeRepository
import com.example.test_6.presentation.passcode.data.IndicatorItem
import com.example.test_6.presentation.passcode.data.PasscodeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasscodeViewModel @Inject constructor(
    private val passcodeRepository: PasscodeRepository
) : ViewModel() {

    private val _enteredPasscode = MutableStateFlow("")
    val enteredPasscode: StateFlow<String> get() = _enteredPasscode

    private val _verificationResult = MutableStateFlow<PasscodeResult?>(null)
    val verificationResult: StateFlow<PasscodeResult?> get() = _verificationResult

    private val _filledIndicators = MutableStateFlow<List<IndicatorItem>>(emptyList())
    val filledIndicators: StateFlow<List<IndicatorItem>> get() = _filledIndicators

    fun handleDigitClick(digit: Char) {
        if (_enteredPasscode.value.length < 4) {
            _enteredPasscode.value += digit
            updateFilledIndicators(_enteredPasscode.value.length)
        }
        if (_enteredPasscode.value.length == 4) {
            verifyPasscode(_enteredPasscode.value)
            resetPassword()
        }
    }

    fun handleDeleteClick() {
        if (_enteredPasscode.value.isNotEmpty()) {
            _enteredPasscode.value =
                _enteredPasscode.value.substring(0, _enteredPasscode.value.length - 1)
            updateFilledIndicators(_enteredPasscode.value.length)
        }
    }

    private fun updateFilledIndicators(enteredPasscodeLength: Int) {
        val filledIndicators = mutableListOf<IndicatorItem>()

        for (i in 0 until enteredPasscodeLength) {
            filledIndicators.add(IndicatorItem(id = i, isFilled = true))
        }
        for (i in enteredPasscodeLength until 4) {
            filledIndicators.add(IndicatorItem(id = i, isFilled = false))
        }
        _filledIndicators.value = filledIndicators
    }

    private fun resetPassword() {
        _enteredPasscode.value = ""
        _filledIndicators.value = emptyList()
    }


    private fun verifyPasscode(inputPasscode: String) {
        viewModelScope.launch {
            val isPasscodeCorrect = passcodeRepository.verifyPasscode(inputPasscode)
            _verificationResult.value = if (isPasscodeCorrect) {
                PasscodeResult.Success("Success")
            } else {
                PasscodeResult.Error("Error")
            }
        }
        _verificationResult.value = null
    }
}