package com.example.rickandmortyapp.feature.character_detail.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.feature.character_detail.model.CharacterDetailsModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharacterDetailsView : Presentable {
    fun initViews(data: CharacterDetailsModel)
}