package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model

data class ListCharacterEntityModel(
    val count: Int,
    val nextPage: String?,
    val list: List<CharacterModel>
)