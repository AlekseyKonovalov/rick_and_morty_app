package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.mapper

import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.domain.entity.ListCharacterEntity
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.ListCharacterEntityModel
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

    fun map(characterEntityList: ListCharacterEntity): ListCharacterEntityModel {
        val list = mutableListOf<CharacterModel>()
        characterEntityList.list.forEach { characterEntity ->
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
        return ListCharacterEntityModel(characterEntityList.count, characterEntityList.nextPage, list)
    }

}