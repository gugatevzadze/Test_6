package com.example.test_6.presentation.passcode

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_6.BaseFragment
import com.example.test_6.databinding.FragmentPasscodeBinding
import com.example.test_6.presentation.passcode.adapters.IndicatorAdapter
import com.example.test_6.presentation.passcode.adapters.KeyboardAdapter
import com.example.test_6.presentation.passcode.adapters.KeyboardItemClickListener
import com.example.test_6.presentation.passcode.data.IndicatorItem
import com.example.test_6.presentation.passcode.data.PasscodeResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PasscodeFragment : BaseFragment<FragmentPasscodeBinding>(FragmentPasscodeBinding::inflate),
    KeyboardItemClickListener {

    private val passcodeViewModel: PasscodeViewModel by viewModels()
    private lateinit var keyboardAdapter: KeyboardAdapter
    private lateinit var indicatorAdapter: IndicatorAdapter

    override fun setUp() {
        adapterInit()
    }

    override fun onClickListeners() {
    }

    override fun bindObservers() {
        resultObserver()
        indicatorUpdater()
    }

    private fun adapterInit() {
        keyboardAdapter = KeyboardAdapter(this)
        indicatorAdapter = IndicatorAdapter()

        binding.recyclerViewKeyboard.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = keyboardAdapter
        }

        binding.recyclerViewIndicator.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = indicatorAdapter
        }
    }

    override fun onDigitClick(value: String) {
        passcodeViewModel.handleDigitClick(value.first())
    }

    override fun onDeleteClick() {
        passcodeViewModel.handleDeleteClick()
    }

    override fun onFingerprintClick() {}

    private fun resultObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            passcodeViewModel.verificationResult.collect { passcodeResult ->
                when (passcodeResult) {
                    is PasscodeResult.Success -> showSuccessMessage(passcodeResult.message)
                    is PasscodeResult.Error -> showErrorMessage(passcodeResult.message)
                    else -> {}
                }
            }
        }
    }

    private fun indicatorUpdater() {
        viewLifecycleOwner.lifecycleScope.launch {
            passcodeViewModel.filledIndicators.collect { filledIndicators ->
                updateIndicator(filledIndicators)
            }
        }
    }

    private fun updateIndicator(filledIndicators: List<IndicatorItem>) {
        indicatorAdapter.submitList(filledIndicators)
    }

    private fun showSuccessMessage(message: String) {
        makeToast(message)
    }

    private fun showErrorMessage(message: String) {
        makeToast(message)
    }

    private fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}