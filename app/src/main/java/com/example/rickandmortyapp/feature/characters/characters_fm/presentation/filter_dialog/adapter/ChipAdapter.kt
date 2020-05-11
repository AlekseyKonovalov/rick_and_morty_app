package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterData
import kotlinx.android.synthetic.main.item_chip.view.*


class ChipAdapter(
    private val onItemClick: (chipModel: ChipModel) -> Unit
) : RecyclerView.Adapter<ChipAdapter.ChipViewHolder>() {

    private var itemsList: List<ChipModel> = mutableListOf()

    fun updateDataSet(newItems: List<ChipModel>) {
        itemsList = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chip, parent, false)
        return ChipViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        return holder.bind(itemsList[position])
    }

    class ChipViewHolder(
        itemView: View,
        private val onItemClick: (chipModel: ChipModel) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(chipModel: ChipModel) {
            itemView.filter_chip.setOnCheckedChangeListener { _, _ -> }
            itemView.filter_chip.isChecked = chipModel.isChecked
            itemView.filter_chip.setOnCheckedChangeListener { _, isChecked ->
                onItemClick.invoke(
                    ChipModel(
                        chipModel.data,
                        isChecked
                    )
                )
            }
            itemView.filter_chip.text = chipModel.data.localeName
        }

    }


}

