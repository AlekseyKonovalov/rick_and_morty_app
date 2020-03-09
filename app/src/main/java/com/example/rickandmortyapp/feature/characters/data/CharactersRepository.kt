package com.example.rickandmortyapp.feature.characters.data

import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity
import com.example.rickandmortyapp.network.RickMortyApi
import io.reactivex.Observable
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickMortyApi: RickMortyApi,
    private val toCharacterEntityMapper: ToCharacterEntityMapper
) {
    fun getAllCharacters(page: Int? = null): Observable<List<CharacterEntity>> {
        return rickMortyApi.getAllCharacters(page)
            .map { toCharacterEntityMapper.map(it) }
    }
}