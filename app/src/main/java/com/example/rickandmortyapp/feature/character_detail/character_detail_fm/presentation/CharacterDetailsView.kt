package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharacterDetailsView : Presentable {
    fun initViews(data: CharacterModel)

    fun setThumbDownActive()
    fun setThumbUpActive()
    fun setThumbInactive()

    fun updateFavoriteIcon(isFavorite: Boolean)
}