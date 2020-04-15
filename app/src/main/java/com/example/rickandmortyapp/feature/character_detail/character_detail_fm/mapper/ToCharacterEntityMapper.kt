package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.mapper

import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import javax.inject.Inject

class ToCharacterEntityMapper @Inject constructor() {
    fun mapToEntity(item: CharacterModel): CharacterEntity {
        return CharacterEntity(
            id = item.id,
            name = item.name,
            status = item.status,
            species = item.species,
            type = item.type,
            gender = item.gender,
            originName = item.originName,
            originUrl = item.originUrl,
            currentLocationName = item.currentLocationName,
            currentLocationUrl = item.currentLocationUrl,
            imageUrl = item.imageUrl,
            episodesList = item.episodesList,
            url = item.url,
            createdDate = item.createdDate,
            isFavorite = item.isFavorite,
            rating = item.rating
        )
    }
}