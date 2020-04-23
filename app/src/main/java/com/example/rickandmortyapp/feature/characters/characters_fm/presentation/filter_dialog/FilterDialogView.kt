package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilterDialogView : MvpView {
    fun initListeners()
}