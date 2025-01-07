package com.muradibadov.androidtask.ui.gender



import android.annotation.SuppressLint
import com.muradibadov.androidtask.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.muradibadov.androidtask.ui.retrofit.GenderResponse
import com.muradibadov.androidtask.ui.retrofit.SimpleApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenderActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvGenderResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gender)

        // Initialize views
        etName = findViewById(R.id.etName)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvGenderResult = findViewById(R.id.tvGenderResult)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.genderize.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val genderizeApi = retrofit.create(SimpleApi::class.java)

        // Handle submit button click
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()

            if (name.isNotEmpty()) {
                // Make the API call
                genderizeApi.getGender(name).enqueue(object : Callback<GenderResponse> {
                    override fun onResponse(
                        call: Call<GenderResponse>,
                        response: Response<GenderResponse>
                    ) {
                        if (response.isSuccessful) {
                            val genderResponse = response.body()
                            val gender = genderResponse?.gender ?: "Unknown"
                            val probability = genderResponse?.probability ?: 0.0

                            // Display result
                            tvGenderResult.text = "Gender: $gender\nProbability: ${"%.2f".format(probability)}"
                        } else {
                            tvGenderResult.text = "Error: Unable to get gender"
                        }
                    }

                    override fun onFailure(call: Call<GenderResponse>, t: Throwable) {
                        tvGenderResult.text = "Error: ${t.message}"
                    }
                })
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

