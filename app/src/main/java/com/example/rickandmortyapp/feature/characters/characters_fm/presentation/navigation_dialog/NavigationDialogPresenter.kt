package com.example.rickandmortyapp.feature.characters.characters_fm.presentation.navigation_dialog

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class NavigationDialogPresenter @Inject constructor(

) : BasePresenter<NavigationMenuView>() {

    override fun onFirstViewAttach() {
        viewState.initListeners()
    }

    fun onProfileClick() {

    }

}