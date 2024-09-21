package com.example.futuramaapi.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futuramaapi.data.api.retrofit.RetrofitService
import com.example.futuramaapi.ui.screens.character.uiState.CharacterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    fun getCharacterInfo(id: Int) {
        viewModelScope.launch {
            val character = retrofitApi.getCharacterById(id)
            val newUiState = _uiState.value.copy(
                character = character,
                isCharacterLoading = false
            )
            _uiState.value = newUiState
            setCharacterEpisodes()
        }
    }

    private suspend fun setCharacterEpisodes() {
        val episodes = retrofitApi.getEpisodes()
        _uiState.value = _uiState.value.copy(
            episodes = episodes.items,
            isEpisodeListLoading = false
        )
        println(episodes)
    }
}