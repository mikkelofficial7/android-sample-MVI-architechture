package com.example.samplemvi.model

import com.google.gson.annotations.SerializedName

class ItemEntries(
    @SerializedName("API") val api: String? = "",
    @SerializedName("Link") val link: String? = "",
    @SerializedName("Category") val category: String? = ""
)