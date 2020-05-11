package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterData
import javax.inject.Inject

class FilterUiMapper @Inject constructor() {
    fun getFilterDataGender(): List<FilterData> {
        return listOf(FilterData.FilterGender("female", "Женщина"),
            FilterData.FilterGender("male", "Мужчина"),
            FilterData.FilterGender("genderless", "Пол отсутствует"),
            FilterData.FilterGender("unknown", "Неизвестно"))
    }

    fun getFilterDataStatus(): List<FilterData> {
        return listOf(FilterData.FilterStatus("alive", "Живой"),
            FilterData.FilterStatus("dead", "Мертвый"),
            FilterData.FilterStatus("unknown", "Неизвестно"))
    }

    fun getFilterDataSpecies(): List<FilterData> {
        return listOf(FilterData.FilterSpecies("human", "Человек"),
            FilterData.FilterSpecies("alien", "Инопланетянин"))
    }
}