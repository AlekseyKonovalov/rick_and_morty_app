package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.*
import javax.inject.Inject

@InjectViewState
class FilterDialogPresenter @Inject constructor(
    private val filterDataBus: FilterDataBus
) : BasePresenter<FilterDialogView>() {

    private var status: FilterStatus? = null
    private var species: FilterSpecies? = null
    private var gender: FilterGender? = null

    override fun onFirstViewAttach() {
        viewState.initListeners()
    }

    fun onStatusClick(status: FilterStatus?) {
        this.status = status
    }

    fun onSpeciesClick(species: FilterSpecies?) {
        this.species = species
    }

    fun onGenderClick(gender: FilterGender?) {
        this.gender = gender
    }

    fun onApplyClick() {
        filterDataBus.emmitData(FilterModel(status, species, gender))
    }

}