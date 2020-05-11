package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model

sealed class FilterData(open val title: String, open val localeName: String) {
    class FilterGender(override val title: String, override val localeName: String) : FilterData(title, localeName)
    class FilterSpecies(override val title: String, override val localeName: String) : FilterData(title, localeName)
    class FilterStatus(override val title: String, override val localeName: String) : FilterData(title, localeName)
}