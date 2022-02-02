package com.example.samplemvi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkRequest {
    private const val BASE_URL = "https://api.publicapis.org/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService: NetworkInterface = getRetrofit().create(NetworkInterface::class.java)
}