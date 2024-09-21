package com.example.futuramaapi.ui.screens.character.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futuramaapi.data.api.models.Episode
import com.example.futuramaapi.databinding.EpisodeViewBinding

class RVEpisodesAdapter():
    RecyclerView.Adapter<RVEpisodesAdapter.EpisodeViewHolder>() {

    var episodes = emptyList<Episode>()

    class EpisodeViewHolder(
        private val binding: EpisodeViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            with(binding) {
                tvEpisodeName.text = episode.name
                tvEpisodeCode.text = episode.productionCode
                tvEpisodeAirDate.text = episode.airDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = EpisodeViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

}