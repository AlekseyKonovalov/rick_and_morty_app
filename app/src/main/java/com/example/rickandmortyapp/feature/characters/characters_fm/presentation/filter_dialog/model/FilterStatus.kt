package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model

enum class FilterStatus(val title: String, val localeName: String) {
    ALIVE("alive", "Живой"),
    DEAD("dead", "Мертвый"),
    UNKNOWN("unknown", "Неизвестно")
}