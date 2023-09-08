package com.example.upgift2kotlin

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    // Declare bottomNavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Setup BottomNavigationView
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_nav -> {
                    // Navigate to HomeActivity
                    val homeIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeIntent)
                    true
                }
                R.id.profile_nav -> {
                    // Navigate to ProfileActivity
                    val profileIntent = Intent(this, ProfileActivity::class.java)
                    startActivity(profileIntent)
                    true
                }
                R.id.form_nav -> {
                    // Navigate to RegistrationActivity (assuming this is your registration screen)
                    val registrationIntent = Intent(this, FormActivity::class.java)
                    startActivity(registrationIntent)
                    true
                }
                R.id.logoutButton -> {
                    performLogout()
                    true
                }
                else -> false
            }
        }
    }

    private fun performLogout() {
        // Redirect to LoginActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()  // Close the HomeActivity
    }
}
