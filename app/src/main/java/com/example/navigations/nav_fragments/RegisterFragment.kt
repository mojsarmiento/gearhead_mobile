package com.example.navigations.nav_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.navigations.R
import com.example.navigations.api.RegisterRequest
import com.example.navigations.api.RegisterResponse
import com.example.navigations.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.navigations.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.tvToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment5)
        }

        binding.btnSignup.setOnClickListener{
            val name = binding.tiename.text.toString()
            val email = binding.tieUsername.text.toString()
            val address = binding.tieAddress.text.toString() // Retrieve address from EditText
            val password = binding.tieconfirmpass.text.toString()
            val confirmPassword = binding.tieconfirmpass2.text.toString()
            val tc = "true" // Define or retrieve tc here

            if (password == confirmPassword) {
                registerUser(name, email, address, password, confirmPassword, tc)

            } else {
                // Show error message to the user that passwords don't match
            }
        }
        return binding.root
    }

    private fun registerUser(name: String, email: String, address: String,
                             password: String, password_confirmation: String, tc: String) {
        val request = RegisterRequest(name, email, address, password, password_confirmation, tc)
        val call = RetrofitClient.apiService.registerUser(request)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message
                    if (!message.isNullOrEmpty()) {
                        showToast(message) // Display message to the user
                    }
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment5)
                } else {
                    // Handle unsuccessful registration
                    // Here you might want to show an error message to the user
                    // You can access the error message from response.errorBody() if needed
                    // For simplicity, let's just show a toast
                    showToast("Registration failed. Please try again.")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                // Handle failure
                // Here you can show an error message to the user
                // For simplicity, let's just show a toast
                showToast("Failed to register. Please check your internet connection.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    }
}