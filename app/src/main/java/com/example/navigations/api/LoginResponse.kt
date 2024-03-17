package com.example.navigations.api

data class LoginResponse(
    val token: String,
    val message: String,
    val status: String
)
