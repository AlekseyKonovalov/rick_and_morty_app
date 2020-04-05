package com.example.rickandmortyapp.feature.data

import com.example.rickandmortyapp.db.entity.CharacterDbEntity
import com.example.rickandmortyapp.feature.domain.entity.CharacterEntity
import com.example.rickandmortyapp.feature.domain.entity.Rating
import com.example.rickandmortyapp.network.responses.CharacterResponse
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() {
    fun mapToEntity(characterResponse: CharacterResponse): List<CharacterEntity> {
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

    fun mapFromEntityToDBEntity(characterEntity: CharacterEntity): CharacterDbEntity {
        return CharacterDbEntity(
            characterEntity.id,
            characterEntity.isFavorite,
            characterEntity.rating.toString()
        )
    }

    fun mapRating(rating: String): Rating {
        return when (rating) {
            "Positive" -> Rating.Positive
            "Negative" -> Rating.Negative
            else -> Rating.Neutral
        }
    }
}