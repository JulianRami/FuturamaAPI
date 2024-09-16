package com.example.futuramaapi.ui.screens.characters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futuramaapi.data.api.models.Character
import com.example.futuramaapi.databinding.CharacterViewBinding

class RVCharactersAdapter(
    private val onEpisodesClickListener: (id: Int) -> Unit
): RecyclerView.Adapter<CharacterPostViewHolder>() {

    var characters = emptyList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterPostViewHolder {
        val binding = CharacterViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterPostViewHolder(
            binding = binding,
            onEpisodesClickListener = onEpisodesClickListener
        )
    }

    override fun onBindViewHolder(holder: CharacterPostViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

}