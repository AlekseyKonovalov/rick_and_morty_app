package com.example.rickandmortyapp.domain.gateway

import com.example.rickandmortyapp.domain.entity.CharacterEntity
import com.example.rickandmortyapp.domain.entity.ListCharacterEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface CharactersRepository {
    fun getAllCharacters(page: Int): Observable<ListCharacterEntity>
    fun getCharactersBySearch(request: String): Observable<ListCharacterEntity>
    fun getCharactersByFilter(
        status: String,
        species: String,
        gender: String,
        page: Int? = null
    ): Observable<ListCharacterEntity>
    fun saveCharacter(item: CharacterEntity): Completable
}