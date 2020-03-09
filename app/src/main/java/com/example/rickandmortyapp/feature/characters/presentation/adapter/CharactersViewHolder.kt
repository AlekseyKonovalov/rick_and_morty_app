package com.example.rickandmortyapp.feature.characters.presentation.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.core.BindedViewHolder
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersViewHolder(
    val view: View,
    private val onItemClick: (characterEntity: CharacterEntity) -> Unit
) : BindedViewHolder<CharacterEntity>(view) {
    override fun bind(data: CharacterEntity) {
        view.item_name.text = data.name
        Glide.with(view.context)
            .load(data.imageUrl)
            .into(view.item_image)
        view.setOnClickListener { onItemClick.invoke(data) }
    }

}
