package com.example.rickandmortyapp.feature.search.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel

private const val PLACEHOLDER_TAG = "PLACEHOLDER_TAG"

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : Presentable {

    fun initViews()
    fun setItems(items: List<CharacterModel>)

    @StateStrategyType(value = AddToEndSingleStrategy::class, tag = PLACEHOLDER_TAG)
    fun showPlaceholder()
    @StateStrategyType(value = AddToEndSingleStrategy::class, tag = PLACEHOLDER_TAG)
    fun hidePlaceholder()
}