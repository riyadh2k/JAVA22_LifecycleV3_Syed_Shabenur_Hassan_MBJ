package com.example.upgift2kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registerButton: Button = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val enteredUsername = usernameEditText.text.toString().trim()
            val enteredPassword = passwordEditText.text.toString().trim()

            val savedUsername = getSharedPreferences("UserPrefs", MODE_PRIVATE).getString("name", "")
            val savedPassword = getSharedPreferences("UserPrefs", MODE_PRIVATE).getString("password", "")

            if (enteredUsername == savedUsername && enteredPassword == savedPassword) {
                // Successful login
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, HomeActivity::class.java) // Change this line to redirect to HomeActivity
                startActivity(intent)
                finish() // This ensures that pressing the back button won't take the user back to the login page
            } else {
                // Failed login
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }


        registerButton.setOnClickListener {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            intent.putExtra("CLEAR_FIELDS", true)  // Add this line
            startActivity(intent)
        }


    }
}
