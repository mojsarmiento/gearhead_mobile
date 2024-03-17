package com.example.navigations.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigations.api.MotorResponse
import com.example.navigations.databinding.ItemMotorCardBinding
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class MotorAdapter :
    ListAdapter<MotorResponse, MotorAdapter.MotorViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorViewHolder {
        val binding =
            ItemMotorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MotorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MotorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MotorViewHolder(private val binding: ItemMotorCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(motor: MotorResponse) {
            binding.apply {
                // Bind your motor data here
                textViewBrand.text = motor.brand
                textViewModel.text = motor.model
                textViewDescription.text = motor.description
                val decimalFormat = DecimalFormat("#,###.##")
                val formattedPrice = "₱ " + decimalFormat.format(motor.price)
                textViewPrice.text = formattedPrice

                imageViewMotor

                // Load image using Picasso or any other library
                //Picasso.get().load(motor.image_path).into(imageViewMotor)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<MotorResponse>() {
        override fun areItemsTheSame(oldItem: MotorResponse, newItem: MotorResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MotorResponse, newItem: MotorResponse): Boolean {
            return oldItem == newItem
        }
    }
}
