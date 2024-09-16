package com.example.futuramaapi.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futuramaapi.data.api.retrofit.RetrofitService
import com.example.futuramaapi.ui.screens.characters.uiState.CharactersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class CharactersViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    init {
        viewModelScope.launch {
            getCharacters()
        }
    }

    private suspend fun getCharacters() {
        val characters = retrofitApi.getCharacters()
        println("Characters received: $characters")
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            characters = characters
        )
    }
}