package com.example.futuramaapi.ui.screens.characters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futuramaapi.data.viewmodel.CharactersViewModel
import com.example.futuramaapi.databinding.ActivityCharactersBinding
import com.example.futuramaapi.ui.screens.character.CharacterActivity
import com.example.futuramaapi.ui.screens.character.CharacterActivity.Companion.CHARACTER_ID
import com.example.futuramaapi.ui.screens.characters.rv.RVCharactersAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val charactersViewModel: CharactersViewModel by viewModels()
    private lateinit var binding: ActivityCharactersBinding
    private lateinit var rvCharactersAdapter: RVCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
    }

    private fun initRV() {
        rvCharactersAdapter = RVCharactersAdapter(
            onViewInfoClickListener = { characterId ->
                launchCharacterActivity(characterId)
            }
        )
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvCharactersAdapter
        }
    }

    private fun launchCharacterActivity(characterId: Int) {
        startActivity(
            Intent(
                this,
                CharacterActivity::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        CHARACTER_ID to characterId
                    )
                )
            }
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            charactersViewModel.uiState.collect { uiState ->
                uiState.characters?.items?.let { charactersList ->
                    rvCharactersAdapter.characters = charactersList
                    rvCharactersAdapter.notifyDataSetChanged()
                }
                binding.rvCharacters.visibility = if (uiState.isLoading) View.INVISIBLE else View.VISIBLE
                binding.pbCharacters.visibility = if (uiState.isLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }
}