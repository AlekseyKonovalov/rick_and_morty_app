package com.example.rickandmortyapp.feature.tab_container.characters.domain

import com.example.rickandmortyapp.feature.tab_container.characters.data.CharactersRepository
import com.example.rickandmortyapp.feature.tab_container.characters.domain.entity.CharacterEntity
import io.reactivex.Observable
import javax.inject.Inject

class CharactersInteractor @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun getAllCharacters(page: Int): Observable<List<CharacterEntity>> {
        return charactersRepository.getAllCharacters(page)
    }
}