package com.example.futuramaapi.data.api.models

import java.util.Date

data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
    val createdAt: Date,
    val image: String
)
