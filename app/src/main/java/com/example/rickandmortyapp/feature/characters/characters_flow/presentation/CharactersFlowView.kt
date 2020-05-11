package com.example.rickandmortyapp.feature.characters.characters_flow.presentation

import com.example.rickandmortyapp.core.view.Presentable
import com.example.rickandmortyapp.navigation.Navigation
import java.util.*

interface CharactersFlowView : Presentable, Navigation {
    fun initRouter(fragmentsList: LinkedList<String>)
}