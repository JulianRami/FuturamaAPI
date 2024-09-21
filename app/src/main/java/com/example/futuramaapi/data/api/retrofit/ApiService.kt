package com.example.futuramaapi.data.api.retrofit

import com.example.futuramaapi.data.api.models.Character
import com.example.futuramaapi.data.api.models.Characters
import com.example.futuramaapi.data.api.models.Episodes
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // https://futuramaapi.com/api/characters
    @GET("characters")
    suspend fun getCharacters(): Characters

    @GET("characters/{character_id}")
    suspend fun getCharacterById(@Path("character_id") id: Int): Character

    @GET("episodes")
    suspend fun getEpisodes() : Episodes

}