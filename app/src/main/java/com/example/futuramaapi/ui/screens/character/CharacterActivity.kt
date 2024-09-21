package com.example.futuramaapi.ui.screens.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futuramaapi.data.viewmodel.CharacterViewModel
import com.example.futuramaapi.databinding.ActivityCharacterBinding
import com.example.futuramaapi.ui.screens.character.rv.RVEpisodesAdapter
import com.example.futuramaapi.utils.loadImage
import kotlinx.coroutines.launch

class CharacterActivity : AppCompatActivity() {

    private lateinit var rvEpisodesAdapter: RVEpisodesAdapter
    private lateinit var binding: ActivityCharacterBinding
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
        getCharacterId()
    }

    private fun initRV() {
        rvEpisodesAdapter = RVEpisodesAdapter()
        binding.rvCharacterEpisodes.apply {
            layoutManager = LinearLayoutManager(this@CharacterActivity)
            adapter = rvEpisodesAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            characterViewModel.uiState.collect { uiState ->
                with(binding) {
                    uiState.character?.let { character ->
                        tvCharacterName.text = character.name
                        tvCharacterSpecie.text = character.species
                        tvCharacterGender.text = character.gender
                        tvCharacterStatus.text = character.status
                        character.image?.let { ivCharacterPhoto.loadImage(it) }
                    }
                    if (uiState.episodes.isNotEmpty()) {
                        rvEpisodesAdapter.episodes = uiState.episodes
                        rvEpisodesAdapter.notifyDataSetChanged()
                    }
                    pbCharacter.visibility = if (uiState.isCharacterLoading) View.VISIBLE else View.INVISIBLE
                    pbEpisodes.visibility = if (uiState.isEpisodeListLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    private fun getCharacterId() {
        val characterId = intent.extras?.getInt(CHARACTER_ID)
        characterId?.let {
            characterViewModel.getCharacterInfo(characterId)
        }
    }

    companion object {
        const val CHARACTER_ID = "characterId"
    }
}