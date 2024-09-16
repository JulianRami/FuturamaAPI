package com.example.futuramaapi.ui.screens.character.uiState

import com.example.futuramaapi.data.api.models.Character
import com.example.futuramaapi.data.api.models.Episode

data class CharacterUiState(
    val character: Character? = null,
    val episodes: List<Episode> = emptyList(),
    val isCharacterLoading: Boolean = true,
    val isEpisodeListLoading: Boolean = true,
)
