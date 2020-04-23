package com.example.rickandmortyapp.feature.characters.characters_fm.presentation

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharactersView : Presentable {
    fun initListeners()
    fun initAdapter()
    fun setItems(items: List<CharacterModel>)
    fun updateCharacterSubscription(paginationSize: Int)
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openMenu()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFilterDialog()
    fun setChipGroup(filterData: FilterModel?)
}