package com.example.rickandmortyapp.network.responses

import com.example.rickandmortyapp.core.net.BaseResponse
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Character>
) : BaseResponse