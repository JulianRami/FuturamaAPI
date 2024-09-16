package com.example.futuramaapi.data.api.models

data class Characters(
    val items: List<Character>,
    val total: Int,
    val page: Int,
    val size: Int,
    val pages: Int
)
