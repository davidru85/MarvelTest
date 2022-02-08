package com.ruizurraca.detail.presentation.custom_views

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.text.util.Linkify
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ruizurraca.detail.R
import com.ruizurraca.detail.databinding.ViewCharacterDetailBinding
import com.ruizurraca.detail.presentation.models.CharactersView

class CharacterDetailView : LinearLayout {

    var binding: ViewCharacterDetailBinding =
        ViewCharacterDetailBinding.inflate(LayoutInflater.from(context), this, true)

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    )
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    )
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        orientation = VERTICAL
    }

    fun clearLinks() {
        binding.characterComicsLinkContainer.removeAllViews()
    }

    fun setValue(character: CharactersView.CharacterView) {
        character.thumbnail?.let { thumbnail ->
            Glide.with(context)
                .load("${thumbnail.path}.${thumbnail.extension}")
                .placeholder(R.drawable.ic_hero_placeholder)
                .into(binding.characterImage)
        }
        binding.characterName.text = character.name
        binding.characterDescription.text = character.description
        binding.characterAppearancesComics.text = "${character.comics?.available}"
        character.getComicsUrl()?.filterNotNull()?.forEach {
            val urlTextView = TextView(context).apply {
                text = it
                Linkify.addLinks(this, Linkify.WEB_URLS)
                layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 20, 0, 0) }
            }
            binding.characterComicsLinkContainer.addView(urlTextView)
        }
    }
}