package com.example.samplemvi.network

import com.example.samplemvi.model.Entries
import retrofit2.http.GET

interface NetworkInterface {
    @GET("entries")
    suspend fun getEntries() : Entries
}