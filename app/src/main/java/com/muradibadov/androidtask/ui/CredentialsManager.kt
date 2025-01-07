package com.muradibadov.androidtask.ui



import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns

class CredentialsManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)


    fun register(email: String, password: String, name: String, phone:String): String {
        val editor = sharedPreferences.edit()
        editor.putString("user_email", email)
        editor.putString("user_password", password)
        editor.putString("user_name", name)
        editor.apply()
        return "Registration Successful"
    }


    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
    fun isPhoneValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches() && phone.length in 10..15
    }

    fun getStoredEmail(): String? {
        return sharedPreferences.getString("user_email", null)
    }


    fun getStoredPassword(): String? {
        return sharedPreferences.getString("user_password", null)
    }


    fun getStoredName(): String? {
        return sharedPreferences.getString("user_name", null)
    }
}
