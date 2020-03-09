package com.example.rickandmortyapp.feature.characters.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharactersView : MvpView {
    fun setItems(items: List<CharacterModel>)
}