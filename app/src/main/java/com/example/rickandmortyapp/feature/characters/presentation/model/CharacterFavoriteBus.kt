package com.example.rickandmortyapp.feature.characters.presentation.model

import com.example.rickandmortyapp.core.data.Bus
import javax.inject.Inject

class CharacterFavoriteBus @Inject constructor(): Bus<CharacterModel>() {
}