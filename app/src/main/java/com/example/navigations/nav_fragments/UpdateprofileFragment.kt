package com.example.navigations.nav_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigations.R
import com.example.navigations.api.ChangePasswordRequest
import com.example.navigations.api.ChangePasswordResponse
import com.example.navigations.api.UpdateProfileRequest
import com.example.navigations.api.UpdateProfileResponse
import com.example.navigations.api.RetrofitClient
import com.example.navigations.api.UserProfileViewModel
import com.example.navigations.databinding.FragmentUpdateprofileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateprofileFragment : Fragment() {
    private lateinit var binding: FragmentUpdateprofileBinding
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateprofileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserProfileViewModel::class.java)

        // Button click listener
        binding.btnChangePassword.setOnClickListener {
            val currentPassword = binding.tieoldpass.text.toString()
            val newPassword = binding.tiechangepass.text.toString()
            val newPasswordConfirmation = binding.tienewpass.text.toString()
            changePassword(currentPassword, newPassword, newPasswordConfirmation)
        }

        binding.btnUpdate.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val address = binding.editTextAddress.text.toString()
            updateProfile(name, address)
        }

        return binding.root
    }

    private fun changePassword(
        currentPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        val request = ChangePasswordRequest(currentPassword, newPassword, newPasswordConfirmation)
        val token = "Bearer 320|zTw6a3W3WXVKw6GCQbsKlSauYru9BpsBBIGRSVU369179986"
        val call = RetrofitClient.apiService.updatePassword(token, request)

        call.enqueue(object : Callback<ChangePasswordResponse> {
            override fun onResponse(
                call: Call<ChangePasswordResponse>,
                response: Response<ChangePasswordResponse>
            ) {
                if (response.isSuccessful) {
                    val changePasswordResponse = response.body()
                    if (changePasswordResponse != null) {
                        showToast("Update Password Changed Successfully.")
                    } else {
                        showToast("Response body is null.")
                    }
                    findNavController().navigate(R.id.action_updateprofileFragment_to_profileFragment)
                } else {
                    // Handle error response here
                    showToast("Error: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                // Handle failure here
                showToast("Failure: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // for update Profile
    private fun updateProfile(name: String, address: String) {
            val request = UpdateProfileRequest(name, address)
            val token = "Bearer 320|zTw6a3W3WXVKw6GCQbsKlSauYru9BpsBBIGRSVU369179986"
            val call = RetrofitClient.apiService.updateProfile(token, request)
            viewModel.setNameAndAddress(name, address)

            call.enqueue(object : Callback<UpdateProfileResponse> {
                override fun onResponse(
                    call: Call<UpdateProfileResponse>,
                    response: Response<UpdateProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        val updateProfileResponse = response.body()
                        if (updateProfileResponse != null) {
                            showToast("Update Profile successfully.")
                        } else {
                            showToast("Response body is null.")
                        }
                        findNavController().navigate(R.id.action_updateprofileFragment_to_profileFragment)
                    } else {
                        // Handle error response here
                        showToast("Error: ${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                    // Handle failure here
                    showToast("Failure: ${t.message}")
                }
            })
        }
}
