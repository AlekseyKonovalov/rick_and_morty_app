package com.example.rickandmortyapp.feature.tab_container.characters.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharactersView : Presentable {
    fun initListeners()
    fun initAdapter()
    fun setItems(items: List<CharacterModel>)
    fun updateCharacterSubscription(paginationSize: Int)
}