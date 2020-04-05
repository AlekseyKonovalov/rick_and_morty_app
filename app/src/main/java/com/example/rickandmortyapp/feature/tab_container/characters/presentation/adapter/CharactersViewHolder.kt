package com.example.rickandmortyapp.feature.tab_container.characters.presentation.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.recycler.BindedViewHolder
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersViewHolder(
    val view: View,
    private val onItemClick: (characterEntity: CharacterModel) -> Unit,
    private val onFavoriteClick: (characterEntity: CharacterModel) -> Unit
) : BindedViewHolder<CharacterModel>(view) {
    override fun bind(data: CharacterModel) {
        with(view) {

            item_container.setOnClickListener {
                if (hasPosition) onItemClick.invoke(data)
            }

            item_character_name.text = data.name
            favorite.setImageDrawable(
                if (data.isFavorite) context.getDrawable(R.drawable.ic_favorite)
                else context.getDrawable(R.drawable.ic_favorite_inactive)
            )
            favorite.setOnClickListener {
                if (hasPosition) onFavoriteClick.invoke(data)
            }
            Glide.with(context)
                .load(data.imageUrl)
                .into(view.item_image)

        }
    }

}
