package com.example.futuramaapi.data.api.models

data class Episodes(
    val items: List<Episode>,
    val total: Int,
    val page: Int,
    val size: Int,
    val pages: Int
)
