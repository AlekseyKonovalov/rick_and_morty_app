package com.example.rickandmortyapp.domain.entity

data class ListCharacterEntity(
    val count: Int,
    val nextPage: String?,
    val list: List<CharacterEntity>
)