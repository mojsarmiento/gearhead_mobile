package com.example.navigations.api

data class RegisterRequest(
    val name: String,
    val email: String,
    val address: String,
    val password: String,
    val password_confirmation: String,
    val tc: String
)
