package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model

data class FilterModel(
    val status: FilterData.FilterStatus?,
    val species: FilterData.FilterSpecies?,
    val gender: FilterData.FilterGender?
)