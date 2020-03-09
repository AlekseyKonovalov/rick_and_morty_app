package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.network.responses.CharacterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {
    @GET("character/")
    fun getAllCharacters(@Query("page") page: Int? = null): Observable<CharacterResponse>

}