package com.muradibadov.androidtask.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muradibadov.androidtask.R
import com.muradibadov.androidtask.ui.login.MainActivity
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.muradibadov.androidtask.ui.CredentialsManager

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Correct the references to TextInputEditText
        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etEmail = findViewById<TextInputEditText>(R.id.etValidEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val checkBoxTerms = findViewById<MaterialCheckBox>(R.id.checkBox)
        val btnNext = findViewById<MaterialButton>(R.id.materialButton)
        val btnLogin = findViewById<MaterialButton>(R.id.buttonLogin)

        // Handle "Next" button click
        val credentialsManager = CredentialsManager(this)
        btnNext.setOnClickListener {
            val fullName = etFullName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && fullName.isNotEmpty() && phone.isNotEmpty()) {
                if (credentialsManager.isEmailValid(email)) {
                    val result = credentialsManager.register(email, password, fullName, phone)
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

            // Handle "Log In" button click
            btnLogin.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}