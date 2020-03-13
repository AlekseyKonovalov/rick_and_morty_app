package com.example.rickandmortyapp.feature.character_detail.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.model.CharacterDetailsModel
import javax.inject.Inject

@InjectViewState
class CharacterDetailsPresenter @Inject constructor(
    private val characterDetailsModel: CharacterDetailsModel
) : BasePresenter<CharacterDetailsView>() {
    override fun onFirstViewAttach() {
        viewState.initViews(characterDetailsModel)
    }
}