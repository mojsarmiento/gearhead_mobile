package com.example.navigations.nav_fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigations.R
import com.example.navigations.api.LoginRequest
import com.example.navigations.api.LoginResponse
import com.example.navigations.api.LogoutRequest
import com.example.navigations.api.LogoutResponse
import com.example.navigations.api.RetrofitClient
import com.example.navigations.api.RetrofitClient.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.navigations.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener{
            val email = binding.tieUsername.text.toString()
            val password = binding.tieconfirmpass.text.toString()
            loginUser(email, password)
        }

        binding.tvToSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment2)
        }

        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        val request = LoginRequest(email, password)
        val call = RetrofitClient.apiService.loginUser(request)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    // Save the token to SharedPreferences
                    saveToken(token)
                    val message = response.body()?.message
                    if (!message.isNullOrEmpty()) {
                        showToast(message) // Display message to the user
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    showToast("Login failed. Please try again.")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showToast("Failed to login. Please check your internet connection.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun saveToken(token: String?) {

        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    // Retrieve the token from SharedPreferences
    private fun getToken(): String? {
        return sharedPreferences.getString("token", null)


    }

    fun logout(token: String) {
        val logoutRequest = LogoutRequest(token)
        val token = "23|6P2UA54KtUsxEdj1uDH8QaWU0UjrNZxyVxXLJkvW6f88dee3"

        val call = apiService.logoutUser(logoutRequest, "Bearer $token")

        call.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                if (response.isSuccessful) {
                    // Handle successful logout
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                // Handle failure
            }
        })
}}
