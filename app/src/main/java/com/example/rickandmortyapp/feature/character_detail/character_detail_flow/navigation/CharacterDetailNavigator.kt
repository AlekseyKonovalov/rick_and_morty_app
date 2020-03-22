package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation

import com.example.rickandmortyapp.core.data.Bus
import com.example.rickandmortyapp.navigation.NavigatorData
import javax.inject.Inject

class CharacterDetailNavigator @Inject constructor() : Bus<NavigatorData>() {}