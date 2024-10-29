package com.example.rvtraining

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val birthDateEditText: EditText = findViewById(R.id.birthDateEditText)
        val genderRadioGroup: RadioGroup = findViewById(R.id.radioGroupGender)
        val signupButton: Button = findViewById(R.id.signupButton)
        val alreadyHaveAccountTextView: TextView = findViewById(R.id.alreadyHaveAccountTextView)

        // Set up the "Already have an account" link to go to LoginActivity
        alreadyHaveAccountTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val birthDate = birthDateEditText.text.toString()

            // Check which radio button is selected
            val selectedGenderId = genderRadioGroup.checkedRadioButtonId
            if (selectedGenderId == -1) {
                Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedGenderRadioButton: RadioButton = findViewById(selectedGenderId)
            val gender = selectedGenderRadioButton.text.toString()

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save data in SharedPreferences
            val sharedPref: SharedPreferences = getSharedPreferences("UserPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putString("USER_NAME", name)
            editor.putString("USER_EMAIL", email)
            editor.putString("USER_PASSWORD", password)
            editor.putString("USER_PHONE", phone)
            editor.putString("USER_GENDER", gender)
            editor.putString("USER_BIRTH_DATE", birthDate)
            editor.apply()

            Toast.makeText(this, "Sign-up Successful!", Toast.LENGTH_SHORT).show()

            // Navigate to LoginActivity after successful signup
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close SignupActivity
        }
    }
}
