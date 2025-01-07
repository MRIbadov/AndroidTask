package com.muradibadov.androidtask.ui.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstances {

    //private const val BASE_URL = "https://api.genderize.io/"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.genderize.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val genderizeApi = retrofit.create(SimpleApi::class.java)
}