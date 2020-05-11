package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.adapter

import com.example.rickandmortyapp.core.recycler.ItemDiffCallback
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel

class CharactersDiffCallback(
    oldItems: List<Any>,
    newItems: List<Any>
) : ItemDiffCallback<Any>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if(oldItem is CharacterModel && newItem is CharacterModel){
            oldItem.id == newItem.id && oldItem.isFavorite == newItem.isFavorite && oldItem.rating == newItem.rating
        } else false
    }

}