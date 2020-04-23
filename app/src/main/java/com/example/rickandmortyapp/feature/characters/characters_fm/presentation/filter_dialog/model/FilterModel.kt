package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model

data class FilterModel(
    val status: FilterStatus?,
    val species: FilterSpecies?,
    val gender: FilterGender?
)