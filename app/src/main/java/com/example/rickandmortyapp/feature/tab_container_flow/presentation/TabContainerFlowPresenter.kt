package com.example.rickandmortyapp.feature.tab_container_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class TabContainerFlowPresenter @Inject constructor() : BasePresenter<TabContainerFlowView>() {
    override fun onFirstViewAttach() {
        viewState.navigateToStartScreen()
    }
}