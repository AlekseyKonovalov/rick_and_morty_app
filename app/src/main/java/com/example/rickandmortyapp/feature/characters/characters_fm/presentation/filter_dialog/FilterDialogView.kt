package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.adapter.ChipModel
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterData

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilterDialogView : MvpView {
    fun initListeners()
    fun initViews(
        statuses: List<ChipModel>,
        genderList: List<ChipModel>,
        species: List<ChipModel>
    )

    fun updateStatuses(statuses: List<ChipModel>)
    fun updatedGenderList(genderList: List<ChipModel>)
    fun updateSpecies(species: List<ChipModel>)

}