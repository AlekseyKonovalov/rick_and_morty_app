package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model

enum class FilterGender(val title: String, val localeName: String) {
    FEMALE("female", "Женщина"),
    MALE("male", "Мужчина"),
    GENDERLESS("genderless", "Пол отсутствует"),
    UNKNOWN("unknown", "Неизвестно")
}