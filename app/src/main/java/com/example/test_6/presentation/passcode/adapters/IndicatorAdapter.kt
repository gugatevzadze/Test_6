package com.example.test_6.presentation.passcode.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_6.R
import com.example.test_6.databinding.IndicatorItemBinding
import com.example.test_6.presentation.passcode.data.IndicatorItem

class IndicatorAdapter :
    ListAdapter<IndicatorItem, IndicatorAdapter.ViewHolder>(IndicatorItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IndicatorItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: IndicatorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(indicatorItem: IndicatorItem) {
            if (indicatorItem.isFilled) {
                binding.fillItem.setImageResource(R.drawable.ic_fill_icon)
            } else {
                binding.fillItem.setImageResource(R.drawable.ic_empty_icon)
            }
        }
    }

    private class IndicatorItemDiffCallback : DiffUtil.ItemCallback<IndicatorItem>() {
        override fun areItemsTheSame(oldItem: IndicatorItem, newItem: IndicatorItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IndicatorItem, newItem: IndicatorItem): Boolean {
            return oldItem == newItem
        }
    }
}
