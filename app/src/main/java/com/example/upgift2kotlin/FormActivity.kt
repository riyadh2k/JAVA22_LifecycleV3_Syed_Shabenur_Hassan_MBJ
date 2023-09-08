package com.example.upgift2kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class FormActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var licenseCheckBox: CheckBox
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        initViews()

        if (!intent.getBooleanExtra("CLEAR_FIELDS", false)) {
            loadData()  // Load data only if CLEAR_FIELDS is not passed or false
        }

        setupSubmitButton()
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.nameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        ageEditText = findViewById(R.id.ageEditText)
        emailEditText = findViewById(R.id.emailEditText)
        licenseCheckBox = findViewById(R.id.licenseCheckBox)
        submitButton = findViewById(R.id.submitButton)
    }

    private fun setupSubmitButton() {
        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val age = ageEditText.text.toString().toInt()
            val hasLicense = licenseCheckBox.isChecked
            val email = emailEditText.text.toString()

            saveData(name, password, age, email, hasLicense)
            // TODO: Implement other logic to send/submit the gathered data.
        }
    }


    private fun saveData(name: String, password: String, age: Int, email: String, hasLicense: Boolean) {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("name", name)
        editor.putString("password", password)
        editor.putInt("age", age)
        editor.putString("email", email)
        editor.putBoolean("hasLicense", hasLicense)

        editor.apply()
        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show()

        // Redirect to ProfileActivity after data is saved
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()  // Close the FormActivity so user can't go back by pressing the back button
    }


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Load data and set to views
        nameEditText.setText(sharedPreferences.getString("name", ""))
        passwordEditText.setText(sharedPreferences.getString("password", ""))
        ageEditText.setText(sharedPreferences.getInt("age", 0).toString())
        emailEditText.setText(sharedPreferences.getString("email", ""))
        licenseCheckBox.isChecked = sharedPreferences.getBoolean("hasLicense", false)
    }
}
