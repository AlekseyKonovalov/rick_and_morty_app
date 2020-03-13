package com.example.rickandmortyapp.feature.characters.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.recycler.BaseAdapter
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterModel

class CharactersAdapter(
    private val onItemClick: (characterEntity: CharacterModel) -> Unit,
    private val onFavoriteClick: (characterEntity: CharacterModel) -> Unit
) : BaseAdapter<CharactersViewHolder>() {

    private var itemsList: MutableList<CharacterModel> = mutableListOf()

    fun updateDataSet(newItems: List<CharacterModel>) {
        val callback = CharactersDiffCallback(this.itemsList, newItems)
        val result = DiffUtil.calculateDiff(callback)
        this.itemsList.clear()
        this.itemsList.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = itemsList.size

    override fun onLayoutRequested(viewType: Int) = R.layout.item_character

    override fun onCreateViewHolder(view: View, viewType: Int) =
        CharactersViewHolder(
            view,
            onItemClick,
            onFavoriteClick
        )

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) =
        holder.bind(itemsList[position])

}

