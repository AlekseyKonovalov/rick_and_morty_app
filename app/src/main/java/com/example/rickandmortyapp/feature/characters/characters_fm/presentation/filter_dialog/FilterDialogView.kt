package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterGender
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterSpecies
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterStatus

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilterDialogView : MvpView {
    fun initListeners()
    fun setStatus(status: FilterStatus)
    fun setSpecies(species: FilterSpecies)
    fun setGender(gender: FilterGender)
}