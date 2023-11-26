package com.pelz.kotlinsampleapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class Register : AppCompatActivity() {
    private lateinit var signUpButton: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextBusinessName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Access the UI elements
        signUpButton = findViewById(R.id.buttonSubmit)
        editTextName = findViewById(R.id.editTextName)
        editTextBusinessName = findViewById(R.id.editTextBusinessName)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)

        // Set a click listener to the sign-up button
        signUpButton.setOnClickListener {
            // Perform registration logic here

            // For now, just show a registration success message
            showRegistrationSuccessMessage()

            // Clear input fields
            clearInputFields()

            // Update the sign-up button state and color after clearing fields
            updateSignUpButton(false)
        }

        val logInButton: Button = findViewById(R.id.logInBtn)

        // Set a listener to the login button
        logInButton.setOnClickListener {
            // Navigate User to Login Page
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        // Set up text change listeners for each EditText
        editTextName.addTextChangedListener(textWatcher)
        editTextBusinessName.addTextChangedListener(textWatcher)
        editTextPhone.addTextChangedListener(textWatcher)
        editTextEmail.addTextChangedListener(textWatcher)
        editTextPassword.addTextChangedListener(textWatcher)

        // Initially set the sign-up button to be disabled and gray
        updateSignUpButton(false)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            // Check if all fields are non-empty
            val allFieldsFilled =
                !s.isNullOrBlank() &&
                        !editTextName.text.isNullOrBlank() &&
                        !editTextBusinessName.text.isNullOrBlank() &&
                        !editTextPhone.text.isNullOrBlank() &&
                        !editTextEmail.text.isNullOrBlank() &&
                        !editTextPassword.text.isNullOrBlank()

            // Update the sign-up button state and color
            updateSignUpButton(allFieldsFilled)
        }
    }

    private fun updateSignUpButton(enable: Boolean) {
        signUpButton.isEnabled = enable
        signUpButton.backgroundTintList = getColorStateList(if (enable) R.color.green else R.color.light_green)
        signUpButton.setTextColor(getColor(R.color.white))
    }


    private fun showRegistrationSuccessMessage() {
        val parentLayout: View = findViewById(android.R.id.content)
        Snackbar.make(parentLayout, "Registration successful", Snackbar.LENGTH_LONG).show()

        // Delay the redirection by 2 seconds
        val handler = Handler()
        handler.postDelayed({
            // Redirect to the login page after successful registration
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }, 1000) // 2000 milliseconds (2 seconds)
    }


    private fun clearInputFields() {
        // Clear input fields here
        editTextName.text.clear()
        editTextBusinessName.text.clear()
        editTextPhone.text.clear()
        editTextEmail.text.clear()
        editTextPassword.text.clear()
    }
}
