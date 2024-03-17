package com.example.navigations.nav_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.navigations.R
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the DrawerLayout
        drawerLayout = view.findViewById(R.id.drawer_layout)

        // Set up the NavigationView
        val navigationView: NavigationView = view.findViewById(R.id.navigation_drawer)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation item clicks here
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    showToast("Home")
                }
                R.id.nav_profile -> {
                    findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
                }
                R.id.nav_accessories -> {
                    findNavController().navigate(R.id.action_homeFragment_to_accessoriesFragment)
                }
                R.id.nav_motorcyles -> {
                    findNavController().navigate(R.id.action_homeFragment_to_motorcycleFragment)
                }
                R.id.nav_cart -> {
                    findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
                }
                R.id.nav_Order_History -> {
                    findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
                }

                R.id.nav_Logout -> {
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    showToast("Logout Successfully")
                }
                // Add more cases for other menu items as needed

                else -> return@setNavigationItemSelectedListener false
            }

            // Close the drawer after handling the click
            drawerLayout.closeDrawer(navigationView)

            true
        }

        // Get the toggle button
        val toggleButton: ImageButton = view.findViewById(R.id.btnToggleDrawer)
        // Set click listener for the toggle button
        toggleButton.setOnClickListener {
            toggleDrawer()
        }

        return view
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}
