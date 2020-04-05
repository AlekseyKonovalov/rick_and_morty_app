package com.example.rickandmortyapp.feature.tab_container.characters.presentation.model

import com.example.rickandmortyapp.core.data.Bus
import javax.inject.Inject

class UpdateCharacterBus @Inject constructor() : Bus<CharacterModel>() {}