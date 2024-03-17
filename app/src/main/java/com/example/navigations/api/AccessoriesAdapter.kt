package com.example.navigations.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigations.api.AccessoriesResponse
import com.example.navigations.databinding.ItemAccessoryBinding
import java.text.DecimalFormat

class AccessoriesAdapter :
    ListAdapter<AccessoriesResponse, AccessoriesAdapter.AccessoryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccessoryViewHolder {
        val binding =
            ItemAccessoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccessoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccessoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AccessoryViewHolder(private val binding: ItemAccessoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(accessory: AccessoriesResponse) {
            binding.apply {
                // Bind accessory data here
                textViewAccessoryName.text = accessory.name
                textViewAccessoryType.text = accessory.type
                textViewAccessoryDescription.text = accessory.description
                val decimalFormat = DecimalFormat("#,###.##")
                val formattedPrice = "₱ " + decimalFormat.format(accessory.price)
                textViewAccessoryPrice.text = formattedPrice

                // Load image using Picasso or any other library
                //Picasso.get().load(accessory.image_path).into(imageViewAccessory)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<AccessoriesResponse>() {
        override fun areItemsTheSame(oldItem: AccessoriesResponse, newItem: AccessoriesResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AccessoriesResponse, newItem: AccessoriesResponse): Boolean {
            return oldItem == newItem
        }
    }
}
