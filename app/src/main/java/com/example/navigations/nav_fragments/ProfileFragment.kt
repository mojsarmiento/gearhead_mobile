package com.example.navigations.nav_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigations.R
import com.example.navigations.api.UserProfileViewModel
import com.example.navigations.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container, false)

        binding.btnEditProfile.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_updateprofileFragment)
        }

        viewModel = ViewModelProvider(requireActivity()).get(UserProfileViewModel::class.java)

        viewModel.name.observe(viewLifecycleOwner) { newName ->
            binding.idName.text = newName
        }

        viewModel.address.observe(viewLifecycleOwner) { newAddress ->
            binding.idAddress.text = newAddress
        }

        return binding.root
    }
}