package com.example.test_6.presentation.passcode.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_6.databinding.KeyboardItemBinding
import com.example.test_6.presentation.passcode.common.KeyboardItemClickListener
import com.example.test_6.presentation.passcode.model.KeyboardItem
import com.example.test_6.presentation.passcode.model.KeyboardType
import com.example.test_6.presentation.passcode.data.PasscodeKeyboardData

class KeyboardAdapter(private val listener: KeyboardItemClickListener) :
    RecyclerView.Adapter<KeyboardAdapter.ViewHolder>() {

    private val keyboardItems = PasscodeKeyboardData.keyboard.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = KeyboardItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = keyboardItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = keyboardItems.size

    inner class ViewHolder(
        private val binding: KeyboardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.digitBtn.setOnClickListener {
                handleDigitClick(keyboardItems[adapterPosition])
            }
            binding.imageBtn.setOnClickListener {
                handleDeleteClick()
            }
            binding.fingerprintBtn.setOnClickListener {
                handleFingerprintClick()
            }
        }

        fun bind(item: KeyboardItem) {
            when (item.type) {
                KeyboardType.DIGIT -> {
                    binding.digitBtn.text = item.value
                    binding.digitBtn.visibility = ViewGroup.VISIBLE
                    binding.imageBtn.visibility = ViewGroup.GONE
                    binding.fingerprintBtn.visibility = ViewGroup.GONE
                }

                KeyboardType.DELETE -> {
                    binding.imageBtn.setImageResource(item.image!!)
                    binding.imageBtn.visibility = ViewGroup.VISIBLE
                    binding.digitBtn.visibility = ViewGroup.GONE
                    binding.fingerprintBtn.visibility = ViewGroup.GONE
                }

                KeyboardType.FINGERPRINT -> {
                    binding.fingerprintBtn.setImageResource(item.image!!)
                    binding.fingerprintBtn.visibility = ViewGroup.VISIBLE
                    binding.digitBtn.visibility = ViewGroup.GONE
                    binding.imageBtn.visibility = ViewGroup.GONE
                }
            }
        }

        private fun handleDigitClick(item: KeyboardItem) {
            listener.onDigitClick(item.value!!)
        }

        private fun handleDeleteClick() {
            listener.onDeleteClick()
        }

        private fun handleFingerprintClick() {
            listener.onFingerprintClick()
        }
    }
}
