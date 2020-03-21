package com.example.rickandmortyapp.feature.tab_container.characters.data

import com.example.rickandmortyapp.feature.tab_container.characters.domain.entity.CharacterEntity
import com.example.rickandmortyapp.network.responses.CharacterResponse
import javax.inject.Inject

class ToCharacterEntityMapper @Inject constructor() {
    fun map(characterResponse: CharacterResponse): List<CharacterEntity> {
        val charactersList = mutableListOf<CharacterEntity>()
        characterResponse.results.forEach {
            charactersList.add(
                CharacterEntity(
                    id = it.id,
                    name = it.name,
                    status = it.status,
                    species = it.species,
                    type = it.type,
                    gender = it.gender,
                    originName = it.origin.name,
                    originUrl = it.origin.url,
                    currentLocationName = it.location.name,
                    currentLocationUrl = it.location.url,
                    imageUrl = it.image,
                    episodesList = it.episode,
                    url = it.url,
                    createdDate = it.created
                )
            )
        }
        return charactersList
    }
}