package com.muradibadov.androidtask.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.muradibadov.androidtask.R
import com.muradibadov.androidtask.ui.register.RegisterActivity
import com.muradibadov.androidtask.ui.gender.GenderActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val checkBoxRemember = findViewById<MaterialCheckBox>(R.id.checkBoxRemember)
        val btnNext = findViewById<MaterialButton>(R.id.materialButton2)
        val btnRegister = findViewById<MaterialButton>(R.id.buttonRegister)

        // Handle "Next" button click
        btnNext.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show()

                // Navigate to GenderActivity
                val intent = Intent(this, GenderActivity::class.java)
                startActivity(intent)
            }
        }

        // Handle "Register Now" button click
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
