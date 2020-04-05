package com.example.rickandmortyapp.feature.tab_container.characters.presentation.model

import com.example.rickandmortyapp.feature.domain.entity.Rating
import java.io.Serializable

data class CharacterModel (
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
    val createdDate: String,
    val isFavorite: Boolean = false,
    val rating: Rating = Rating.Neutral
): Serializable