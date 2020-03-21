package com.example.rickandmortyapp.feature.tab_container.characters.domain.entity

data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originName: String,
    val originUrl: String,
    val currentLocationName: String,
    val currentLocationUrl: String,
    val imageUrl: String,
    val episodesList: List<String>,
    val url: String,
    val createdDate: String
)