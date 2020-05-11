package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.adapter

import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterData

data class ChipModel(
    val data: FilterData,
    var isChecked: Boolean = false
)