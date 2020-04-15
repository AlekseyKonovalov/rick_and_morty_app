package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model

import com.example.rickandmortyapp.core.data.Bus
import javax.inject.Inject

class UpdateCharacterBus @Inject constructor() : Bus<CharacterModel>() {}