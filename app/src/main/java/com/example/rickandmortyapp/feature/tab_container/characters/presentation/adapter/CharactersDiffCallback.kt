package com.example.rickandmortyapp.feature.tab_container.characters.presentation.adapter

import com.example.rickandmortyapp.core.recycler.ItemDiffCallback
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel

class CharactersDiffCallback(
    oldItems: List<CharacterModel>,
    newItems: List<CharacterModel>
) : ItemDiffCallback<CharacterModel>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.isFavorite == newItem.isFavorite && oldItem.rating == newItem.rating
    }

}