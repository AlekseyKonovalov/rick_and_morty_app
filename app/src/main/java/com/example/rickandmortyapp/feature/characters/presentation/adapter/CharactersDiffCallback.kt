package com.example.rickandmortyapp.feature.characters.presentation.adapter

import com.example.rickandmortyapp.core.ItemDiffCallback
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity

class CharactersDiffCallback(
    oldItems: List<CharacterEntity>,
    newItems: List<CharacterEntity>
) : ItemDiffCallback<CharacterEntity>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem == newItem
    }

}