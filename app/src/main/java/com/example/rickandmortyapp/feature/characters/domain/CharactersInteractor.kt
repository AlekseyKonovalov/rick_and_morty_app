package com.example.rickandmortyapp.feature.characters.domain

import com.example.rickandmortyapp.feature.characters.data.CharactersRepository
import com.example.rickandmortyapp.feature.characters.domain.entity.CharacterEntity
import io.reactivex.Observable
import javax.inject.Inject

class CharactersInteractor @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun getAllCharacters(page: Int? = null): Observable<List<CharacterEntity>> {
        return charactersRepository.getAllCharacters()
    }
}