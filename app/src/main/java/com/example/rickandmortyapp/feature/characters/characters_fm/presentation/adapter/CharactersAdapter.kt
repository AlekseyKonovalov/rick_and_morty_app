package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.util.gone
import com.example.rickandmortyapp.core.util.show
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.LoadingModel
import kotlinx.android.synthetic.main.item_loading.view.*


class CharactersAdapter(
    private val onItemClick: (characterEntity: CharacterModel) -> Unit,
    private val onFavoriteClick: (characterEntity: CharacterModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemsList: MutableList<Any> = mutableListOf()

    fun updateDataSet(newItems: List<Any>) {
        val callback = CharactersDiffCallback(this.itemsList, newItems)
        val result = DiffUtil.calculateDiff(callback)
        this.itemsList.clear()
        this.itemsList.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = itemsList.size


    override fun getItemViewType(position: Int): Int {
        return if (itemsList[position] is CharacterModel) {
            TYPE_ITEM
        } else {
            TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
            CharactersViewHolder(view, onItemClick, onFavoriteClick)

        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ITEM) {
            (holder as CharactersViewHolder).bind(itemsList[position] as CharacterModel)
        } else {
            (holder as LoadingViewHolder).bind(itemsList[position] as LoadingModel)
        }
    }

    internal class LoadingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(data: LoadingModel) {
            when {
                data.isShowError -> {
                    itemView.progress.gone()
                    itemView.noItemsFound.text = data.error
                    itemView.noItemsFound.show()
                }
                data.isShowLoading -> {
                    itemView.progress.show()
                    disableError()
                }
                else -> {
                    itemView.gone()
                    disableError()
                }
            }
        }

        private fun disableError() {
            itemView.noItemsFound.text = ""
            itemView.noItemsFound.gone()
        }

    }

    companion object {
        private const val TYPE_ITEM = 1
        private const val TYPE_LOADING = 2
    }


}

