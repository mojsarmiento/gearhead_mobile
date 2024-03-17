package com.example.navigations.api

import android.media.Image

data class OrdersRequest(
    val user_id: Int,
    val quantity: Int,
    val total_amount: Int,
    val payment_method: String,
    val status: String,
    val product_names: String,
    val product_images: String,
)
