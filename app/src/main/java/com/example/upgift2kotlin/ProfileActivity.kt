package com.example.upgift2kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var passwordTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var drivingLicenceBooleanView: TextView
    private lateinit var bottomNavigationView: BottomNavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        loadData()
        setupBottomNavigationView()
    }

    private fun initViews() {
        nameTextView = findViewById(R.id.nameTextView)
        passwordTextView = findViewById(R.id.passwordTextView)
        emailTextView = findViewById(R.id.emailTextView)
        ageTextView = findViewById(R.id.ageTextView)
        drivingLicenceBooleanView = findViewById(R.id.drivingLicenceBooleanView)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Load data from shared preferences and set to the TextViews
        nameTextView.text = "Name: " + sharedPreferences.getString("name", "")
        passwordTextView.text = "Password: " + sharedPreferences.getString("password", "")
        emailTextView.text = "Email: " + sharedPreferences.getString("email", "")
        ageTextView.text = "Age: " + sharedPreferences.getInt("age", 0).toString()
        val hasLicense = sharedPreferences.getBoolean("hasLicense", false)
        drivingLicenceBooleanView.text = "Driving License: " + if (hasLicense) "Yes" else "No"
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
        finish()  // Close the ProfileActivity
    }

}
