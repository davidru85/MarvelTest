package com.ruizurraca.marveltest.presentation.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruizurraca.marveltest.R
import com.ruizurraca.marveltest.databinding.ItemCharacterBinding
import com.ruizurraca.marveltest.presentation.home.models.CharactersView

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterHolder>() {

    var listener: CharacterClickListener? = null
    private var characters = mutableListOf<CharactersView.CharacterView>()

    fun fillData(charactersView: CharactersView) {
        charactersView.results?.let { newItems ->
            this.characters.addAll(newItems.toMutableList().filterNotNull())
            notifyDataSetChanged()
        }
    }

    fun empty() {
        characters = mutableListOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterHolder(binding, parent.context)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        characters.get(position).let { character ->
            holder.bind(character)
        }
    }

    inner class CharacterHolder(
        itemCharacterBinding: ItemCharacterBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemCharacterBinding.root) {
        private val binding = itemCharacterBinding

        fun bind(character: CharactersView.CharacterView) {
            binding.card.setOnClickListener { listener?.characterClick(character) }

            binding.name.text = character.name
            character.thumbnail?.let { thumbnail ->
                Glide.with(context)
                    .load("${thumbnail.path}.${thumbnail.extension}")
                    .placeholder(R.drawable.ic_hero_placeholder)
                    .into(binding.imageView)
            }
        }
    }
}

interface CharacterClickListener {
    fun characterClick(character: CharactersView.CharacterView)
}
