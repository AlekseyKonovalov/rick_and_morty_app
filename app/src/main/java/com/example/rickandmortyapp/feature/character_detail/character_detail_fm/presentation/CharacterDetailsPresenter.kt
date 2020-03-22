package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.navigation.*
import javax.inject.Inject

@InjectViewState
class CharacterDetailsPresenter @Inject constructor(
    private val characterDetailNavigator: CharacterDetailNavigator
) : BasePresenter<CharacterDetailsView>() {

    private lateinit var characterDetailsModel: CharacterDetailsModel

    fun setData(characterDetailsModel: CharacterDetailsModel) {
        this.characterDetailsModel = characterDetailsModel
        viewState.initViews(characterDetailsModel)
    }


    fun onBackPressed() {
        characterDetailNavigator.emmitData(
            NavigatorData(Command.Remove, ScreenData(Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL))
        )
    }
}