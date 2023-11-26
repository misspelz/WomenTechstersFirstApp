package com.pelz.kotlinsampleapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LogIn : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // Access the UI elements
        loginButton = findViewById(R.id.buttonSubmit)
        editTextUsername = findViewById(R.id.Username)
        editTextPassword = findViewById(R.id.Password)


        // Set a listener to the login button
        loginButton.setOnClickListener {
            // Navigate User to Main Page
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Set up text change listeners for each EditText
        editTextUsername.addTextChangedListener(textWatcher)
        editTextPassword.addTextChangedListener(textWatcher)

        // Initially set the login button to be disabled and gray
        updateLoginButton(false)

        var regButton: Button = findViewById(R.id.newAccount)

        regButton.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            // Check if all fields are non-empty
            val allFieldsFilled =
                !s.isNullOrBlank() &&
                        !editTextUsername.text.isNullOrBlank() &&
                        !editTextPassword.text.isNullOrBlank()

            // Update the login button state and color
            updateLoginButton(allFieldsFilled)
        }
    }

    private fun updateLoginButton(enable: Boolean) {
        loginButton.isEnabled = enable
        loginButton.backgroundTintList = getColorStateList(if (enable) R.color.green else R.color.light_green)
        loginButton.setTextColor(getColor(R.color.white))
    }
}
