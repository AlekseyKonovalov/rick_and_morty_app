package com.example.rickandmortyapp.feature.characters.presentation.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.BindedViewHolder
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterModel
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersViewHolder(
    val view: View,
    private val onItemClick: (characterEntity: CharacterModel) -> Unit,
    private val onFavoriteClick: (characterEntity: CharacterModel) -> Unit
) : BindedViewHolder<CharacterModel>(view) {
    override fun bind(data: CharacterModel) {
        view.item_name.text = data.name
        view.favorite.setImageDrawable(
            if (data.isFavorite) view.context.getDrawable(R.drawable.ic_favorite)
            else view.context.getDrawable(R.drawable.ic_favorite_inactive)
        )
        view.favorite.setOnClickListener { onFavoriteClick.invoke(data) }
        Glide.with(view.context)
            .load(data.imageUrl)
            .into(view.item_image)
        view.setOnClickListener {
            onItemClick.invoke(data)
        }
    }

}
