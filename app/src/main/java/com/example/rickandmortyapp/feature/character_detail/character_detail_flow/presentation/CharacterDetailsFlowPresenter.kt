package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import javax.inject.Inject

@InjectViewState
class CharacterDetailsFlowPresenter @Inject constructor(
    private val characterDetailsModel: CharacterDetailsModel
) : BasePresenter<CharacterDetailsFlowView>() {
    override fun onFirstViewAttach() {
        viewState.navigateToStartScreen(characterDetailsModel)
    }
}