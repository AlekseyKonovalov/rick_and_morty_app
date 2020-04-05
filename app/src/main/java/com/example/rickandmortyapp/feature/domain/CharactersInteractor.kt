package com.example.rickandmortyapp.feature.domain

import com.example.rickandmortyapp.feature.data.CharactersRepository
import com.example.rickandmortyapp.feature.domain.entity.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CharactersInteractor @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun getAllCharacters(page: Int): Observable<List<CharacterEntity>> {
        return charactersRepository.getAllCharacters(page)
    }

    fun saveCharacter(item: CharacterEntity): Completable {
        return charactersRepository.saveCharacter(item)
    }
}