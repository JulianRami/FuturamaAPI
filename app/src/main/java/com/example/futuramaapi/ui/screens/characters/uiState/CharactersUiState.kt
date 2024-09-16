package com.example.futuramaapi.ui.screens.characters.uiState

import com.example.futuramaapi.data.api.models.Characters

data class CharactersUiState(
    val characters: Characters? = null,
    val isLoading: Boolean = true
)
