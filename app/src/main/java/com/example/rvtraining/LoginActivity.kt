package com.example.rvtraining

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val signUpLink: TextView = findViewById(R.id.signUpLink)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Retrieve saved user data from SharedPreferences
            val sharedPref: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
            val savedEmail = sharedPref.getString("USER_EMAIL", null)
            val savedPassword = sharedPref.getString("USER_PASSWORD", null)

            // Validate login credentials
            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                // Navigate to HomepageActivity
                val intent = Intent(this, HomepageActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
        signUpLink.setOnClickListener {
            // Navigate to SignInActivity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }


}
