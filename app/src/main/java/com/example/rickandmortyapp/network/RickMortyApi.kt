package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.network.responses.CharacterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {
    @GET("character/")
    fun getAllCharacters(@Query("page") page: Int): Observable<CharacterResponse>

    @GET("character/")
    fun getCharactersBySearch(@Query("name") request: String): Observable<CharacterResponse>

    @GET("character/")
    fun getCharactersByFilter(@Query("status") status: String,
                              @Query("species") species: String,
                              @Query("gender") gender: String): Observable<CharacterResponse>

}