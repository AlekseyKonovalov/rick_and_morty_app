package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.mapper

import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import javax.inject.Inject

class FromCharacterEntityMapper @Inject constructor() {
    fun map(characterEntity: CharacterEntity) = CharacterModel(
        id = characterEntity.id,
        name = characterEntity.name,
        status = characterEntity.status,
        species = characterEntity.species,
        type = characterEntity.type,
        gender = characterEntity.gender,
        originName = characterEntity.originName,
        originUrl = characterEntity.originUrl,
        currentLocationName = characterEntity.currentLocationName,
        currentLocationUrl = characterEntity.currentLocationUrl,
        imageUrl = characterEntity.imageUrl,
        episodesList = characterEntity.episodesList,
        url = characterEntity.url,
        createdDate = characterEntity.createdDate
    )

    fun map(characterEntityList: List<CharacterEntity>): List<CharacterModel> {
        val list = mutableListOf<CharacterModel>()
        characterEntityList.forEach { characterEntity ->
            list.add(
                CharacterModel(
                    id = characterEntity.id,
                    name = characterEntity.name,
                    status = characterEntity.status,
                    species = characterEntity.species,
                    type = characterEntity.type,
                    gender = characterEntity.gender,
                    originName = characterEntity.originName,
                    originUrl = characterEntity.originUrl,
                    currentLocationName = characterEntity.currentLocationName,
                    currentLocationUrl = characterEntity.currentLocationUrl,
                    imageUrl = characterEntity.imageUrl,
                    episodesList = characterEntity.episodesList,
                    url = characterEntity.url,
                    createdDate = characterEntity.createdDate,
                    isFavorite = characterEntity.isFavorite,
                    rating = characterEntity.rating
                )
            )
        }
        return list
    }

}