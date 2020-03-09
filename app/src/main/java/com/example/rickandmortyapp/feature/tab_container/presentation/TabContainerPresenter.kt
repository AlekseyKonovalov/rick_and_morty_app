package com.example.rickandmortyapp.feature.tab_container.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class TabContainerPresenter @Inject constructor() : BasePresenter<TabContainerView>() {

    override fun onFirstViewAttach() {
        viewState.setFragment(TabContainerFragment.CHARACTERS)
    }

    fun onClickTab(keyFragment: String) {
        viewState.setFragment(keyFragment)
    }
}