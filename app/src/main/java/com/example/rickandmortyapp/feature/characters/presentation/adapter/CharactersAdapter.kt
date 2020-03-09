package com.example.rickandmortyapp.feature.characters.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.BaseAdapter
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity

class CharactersAdapter(
    private val onItemClick: (characterEntity: CharacterEntity) -> Unit
) : BaseAdapter<CharactersViewHolder>() {

    var items: List<CharacterEntity> = listOf()
        set(value) {
            val callback =
                CharactersDiffCallback(
                    field,
                    value
                )
            val result = DiffUtil.calculateDiff(callback)
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun getItemCount() = items.size

    override fun onLayoutRequested(viewType: Int) = R.layout.item_character

    override fun onCreateViewHolder(view: View, viewType: Int) =
        CharactersViewHolder(
            view,
            onItemClick
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) =
        holder.bind(items[position])

}

