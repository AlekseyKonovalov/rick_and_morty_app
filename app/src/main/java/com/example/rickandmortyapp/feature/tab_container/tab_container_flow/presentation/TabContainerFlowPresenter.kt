package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.navigation.AppNavigator
import com.example.rickandmortyapp.navigation.TabContainerNavigator
import javax.inject.Inject

@InjectViewState
class TabContainerFlowPresenter @Inject constructor(
    private val appNavigator: AppNavigator,
    private val tabContainerNavigator: TabContainerNavigator
) : BasePresenter<TabContainerFlowView>() {
    override fun onFirstViewAttach() {
        viewState.navigateToStartScreen()
    }
}