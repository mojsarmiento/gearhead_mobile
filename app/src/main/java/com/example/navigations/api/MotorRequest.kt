package com.example.navigations.api

import android.media.Image

data class MotorRequest(
    val brand: String,
    val model: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val image_path: Image
)
