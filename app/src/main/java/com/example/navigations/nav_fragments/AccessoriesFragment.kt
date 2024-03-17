package com.example.navigations.nav_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigations.api.AccessoriesAdapter
import com.example.navigations.api.AccessoriesResponse
import com.example.navigations.api.RetrofitClient
import com.example.navigations.databinding.FragmentAccessoriesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccessoriesFragment : Fragment() {

    private lateinit var binding: FragmentAccessoriesBinding
    private lateinit var accessoriesAdapter: AccessoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccessoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and AccessoriesAdapter
        accessoriesAdapter = AccessoriesAdapter()

        // Set up RecyclerView with LinearLayoutManager
        binding.recyclerViewAccessories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accessoriesAdapter
        }

        // Fetch accessories data
        fetchAccessories()
    }

    private fun fetchAccessories() {
        // Call your Retrofit service here to fetch accessories data
        RetrofitClient.apiService.accessoriesUser().enqueue(object : Callback<List<AccessoriesResponse>> {
            override fun onResponse(call: Call<List<AccessoriesResponse>>, response: Response<List<AccessoriesResponse>>) {
                if (response.isSuccessful) {
                    // Update the adapter with the received data
                    val accessories = response.body() ?: emptyList()
                    accessoriesAdapter.submitList(accessories)
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<AccessoriesResponse>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
