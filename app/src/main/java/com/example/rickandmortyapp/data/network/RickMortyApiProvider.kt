package com.example.rickandmortyapp.data.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class RickMortyApiProvider @Inject constructor(private val retrofit: Retrofit) :
    Provider<RickMortyApi> {

    override fun get(): RickMortyApi = retrofit.create(RickMortyApi::class.java)
}