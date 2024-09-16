package com.example.futuramaapi.ui.screens.characters.rv

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.futuramaapi.R
import com.example.futuramaapi.data.api.models.Character
import com.example.futuramaapi.databinding.CharacterViewBinding
import com.example.futuramaapi.utils.loadCircleImage
import com.example.futuramaapi.utils.showToast

class CharacterPostViewHolder(
    private val binding: CharacterViewBinding,
    private val onEpisodesClickListener: (position: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        with(binding) {
            btnEpisodes.setOnClickListener {
                onEpisodesClickListener(character.id)
            }

            tvCharacterName.text = tvCharacterName.context.getString(
                R.string.character_name_with_position,
                character.id,
                character.name
            )

            tvCharacterGender.text =
                tvCharacterGender.context.getString(R.string.character_gender, character.gender)

            ivCharacterPicture.loadCircleImage(character.image)

            ivCharacterStatus.apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                    ivCharacterStatus.context,
                    when(character.status) {
                        "ALIVE" -> R.drawable.ic_alive
                        "DEAD" -> R.drawable.ic_dead
                        else -> R.drawable.ic_unknown
                    }
                ))
                setColorFilter(
                    ContextCompat.getColor(
                    ivCharacterStatus.context,
                    when(character.status) {
                        "ALIVE" -> R.color.green
                        "DEAD" -> R.color.red
                        else -> R.color.blue
                    }
                ))
                setOnClickListener {
                    with(context) {
                        showToast(
                            getString(
                                when(character.status) {
                                    "ALIVE" -> R.string.status_alive
                                    "DEAD" -> R.string.status_dead
                                    else -> R.string.status_unknown
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}