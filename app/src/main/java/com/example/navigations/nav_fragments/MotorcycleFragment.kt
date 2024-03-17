package com.example.navigations.nav_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigations.R
import com.example.navigations.api.AddtoCartRequest
import com.example.navigations.api.AddtoCartResponse
import com.example.navigations.api.ApiService
import com.example.navigations.api.MotorAdapter
import com.example.navigations.api.MotorResponse
import com.example.navigations.api.RetrofitClient
import com.example.navigations.databinding.FragmentMotorcycleBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MotorcycleFragment : Fragment() {


    private lateinit var binding: FragmentMotorcycleBinding
    private lateinit var apiService: ApiService
    private lateinit var motorAdapter: MotorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMotorcycleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        apiService = RetrofitClient.apiService
        motorAdapter = MotorAdapter()

        binding.recycleMotor.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = motorAdapter
        }

        fetchMotorcycles()

    }

    private fun fetchMotorcycles() {
        apiService.motorUser().enqueue(object : Callback<List<MotorResponse>> {
            override fun onResponse(
                call: Call<List<MotorResponse>>,
                response: Response<List<MotorResponse>>
            ) {
                if (response.isSuccessful) {
                    val motorcycles = response.body()
                    motorcycles?.let {
                        motorAdapter.submitList(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<MotorResponse>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun addToCart(itemType: String, id: Int) {
        val apiService = RetrofitClient.apiService
        val addToCartRequest = AddtoCartRequest(itemType, id)
        val call = apiService.addToCart(addToCartRequest)

        call.enqueue(object : Callback<AddtoCartResponse> {
            override fun onResponse(
                call: Call<AddtoCartResponse>,
                response: Response<AddtoCartResponse>
            ) {
                if (response.isSuccessful) {
                    val addToCartResponse = response.body()
                    if (addToCartResponse?.success == "true") {
                        // Show success message to user
                        showToast("Item added to cart successfully")
                    } else {
                        // Show error message from addToCartResponse.message
                        showToast("Failed to add item to cart: ${addToCartResponse?.message}")
                    }
                } else {
                    // Handle unsuccessful response
                    showToast("Failed to add item to cart")
                }
            }

            override fun onFailure(call: Call<AddtoCartResponse>, t: Throwable) {
                // Handle failure
                showToast("Failed to add item to cart: ${t.message}")
            }
        })
    }
    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}