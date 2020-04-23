package com.example.rickandmortyapp.domain

import com.example.rickandmortyapp.data.CharactersRepository
import com.example.rickandmortyapp.domain.entity.CharacterEntity
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

    fun getCharactersBySearch(request: String): Observable<List<CharacterEntity>> {
        return charactersRepository.getCharactersBySearch(request)
    }

    fun getCharactersByFilter(status: String, species: String, gender: String): Observable<List<CharacterEntity>> {
        return charactersRepository.getCharactersByFilter(status, species, gender)
    }
}