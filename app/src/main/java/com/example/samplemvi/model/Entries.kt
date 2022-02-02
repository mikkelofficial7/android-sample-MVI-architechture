package com.example.samplemvi.model

import com.google.gson.annotations.SerializedName

class Entries(
    @SerializedName("count") val totalList: Int,
    @SerializedName("entries") val listEntries: List<ItemEntries>,
)