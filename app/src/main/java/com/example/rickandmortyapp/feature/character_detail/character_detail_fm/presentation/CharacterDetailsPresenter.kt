package com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.ResourceProvider
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation.CharacterDetailNavigator
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import javax.inject.Inject

@InjectViewState
class CharacterDetailsPresenter @Inject constructor(
    private val characterDetailNavigator: CharacterDetailNavigator,
    private val resourceProvider: ResourceProvider
) : BasePresenter<CharacterDetailsView>() {

    private lateinit var characterDetailsModel: CharacterModel

    fun setData(characterDetailsModel: CharacterModel) {
        this.characterDetailsModel = characterDetailsModel
        viewState.initViews(characterDetailsModel)
        viewState.updateFavoriteIcon(characterDetailsModel.isFavorite)
    }

    fun onThumbUpClick() {
        //todo save to db
        viewState.setThumbUpActive()
    }

    fun onThumbDownClick() {
        //todo save to db
        viewState.setThumbDownActive()
    }

    fun onOnFavoriteIconClick(){
        //todo update subject
        characterDetailsModel = characterDetailsModel.copy(isFavorite = !characterDetailsModel.isFavorite)
        viewState.updateFavoriteIcon(characterDetailsModel.isFavorite)
    }

}