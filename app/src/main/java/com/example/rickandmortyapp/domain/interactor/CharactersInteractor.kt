package com.example.rickandmortyapp.domain.interactor

import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.domain.entity.ListCharacterEntity
import com.example.rickandmortyapp.domain.gateway.CharactersRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CharactersInteractor @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun getAllCharacters(page: Int): Observable<ListCharacterEntity> {
        return charactersRepository.getAllCharacters(page)
    }

    fun saveCharacter(item: CharacterEntity): Completable {
        return charactersRepository.saveCharacter(item)
    }

    fun getCharactersBySearch(request: String): Observable<ListCharacterEntity> {
        return charactersRepository.getCharactersBySearch(request)
    }

    fun getCharactersByFilter(status: String, species: String, gender: String, page: Int): Observable<ListCharacterEntity> {
        return charactersRepository.getCharactersByFilter(status, species, gender, page)
    }
}