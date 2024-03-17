package com.example.navigations.api

data class AccessoriesRequest(
    val name: String,
    val type: String,
    val description: String,
    val price: Int,
    val quantity: Int,
    val image_path: String
)
