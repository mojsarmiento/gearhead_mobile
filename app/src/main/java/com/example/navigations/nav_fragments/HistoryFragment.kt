package com.example.navigations.nav_fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigations.R
import com.example.navigations.api.ApiService
import com.example.navigations.api.OrdersAdapter
import com.example.navigations.api.OrdersRequest
import com.example.navigations.api.RetrofitClient
import com.example.navigations.databinding.FragmentHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OrdersAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.buttonBackToProfile.setOnClickListener{
            findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        adapter = OrdersAdapter(emptyList()) // Initialize with empty list initially

        // Call your API method here
        fetchOrders()
    }

    private fun fetchOrders() {
        val authToken = getAuthToken()
        val apiService = RetrofitClient.apiService
        val call = apiService.ordersUser("Bearer $authToken")
        call.enqueue(object : Callback<List<OrdersRequest>> {
            override fun onResponse(
                call: Call<List<OrdersRequest>>,
                response: Response<List<OrdersRequest>>
            ) {
                if (response.isSuccessful) {
                    val ordersList = response.body()
                    if (ordersList != null) {
                        adapter.updateOrders(ordersList)
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<OrdersRequest>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun getAuthToken(): String {
        return sharedPreferences.getString("273|3pbyPbYV4Xe1ztaaCD5BzYZQrUnm56KStUPnOce477a0b9ce", "") ?: ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


