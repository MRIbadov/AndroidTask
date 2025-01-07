package com.muradibadov.androidtask.ui.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


interface SimpleApi {
    @GET("https://api.genderize.io")
    fun getGender(@Query("name") name: String):  Call<GenderResponse>
}
data class GenderResponse(
    val name: String,
    val gender: String?,
    val probability: Float?,
    val count: Int?
)