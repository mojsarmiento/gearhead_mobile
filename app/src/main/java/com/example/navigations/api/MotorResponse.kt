package com.example.navigations.api

data class MotorResponse(
    val brand: String,
    val model: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val image_path: String,
    val id: Int
)
