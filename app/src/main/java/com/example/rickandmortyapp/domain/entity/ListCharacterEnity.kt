package com.example.rickandmortyapp.domain.entity

data class ListCharacterEntity(
    val count: Int,
    val list: List<CharacterEntity>
)