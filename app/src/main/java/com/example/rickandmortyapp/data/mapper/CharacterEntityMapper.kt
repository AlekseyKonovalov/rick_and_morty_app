package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.data.db.entity.CharacterDbEntity
import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.domain.entity.ListCharacterEntity
import com.example.rickandmortyapp.domain.entity.Rating
import com.example.rickandmortyapp.data.network.responses.CharacterResponse
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() {
    fun mapToEntity(characterResponse: CharacterResponse): ListCharacterEntity {
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
        return ListCharacterEntity(
            count = characterResponse.info.count,
            nextPage = characterResponse.info.next,
            list = charactersList
        )
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