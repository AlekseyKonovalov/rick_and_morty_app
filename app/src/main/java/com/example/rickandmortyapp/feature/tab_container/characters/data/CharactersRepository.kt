package com.example.rickandmortyapp.feature.tab_container.characters.data

import com.example.rickandmortyapp.feature.tab_container.characters.domain.entity.CharacterEntity
import com.example.rickandmortyapp.network.RickMortyApi
import io.reactivex.Observable
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val rickMortyApi: RickMortyApi,
    private val toCharacterEntityMapper: ToCharacterEntityMapper
) {
    fun getAllCharacters(page: Int): Observable<List<CharacterEntity>> {
        return rickMortyApi.getAllCharacters(page)
            .map { toCharacterEntityMapper.map(it) }
    }
}