package com.example.futuramaapi.data.api.models

import com.google.gson.annotations.SerializedName

data class Episode(
    val id: Int,
    val name: String,
    val number: Int,
    val productionCode: String,
    val airDate: String,
    @SerializedName("episode")
    val duration: Int,
    val broadcastCode: String
)
