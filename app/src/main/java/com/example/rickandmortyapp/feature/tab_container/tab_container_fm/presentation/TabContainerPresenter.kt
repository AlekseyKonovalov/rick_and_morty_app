package com.example.rickandmortyapp.feature.tab_container.tab_container_fm.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation.TabContainerNavigator
import javax.inject.Inject

@InjectViewState
class TabContainerPresenter @Inject constructor(
    private val tabContainerNavigator: TabContainerNavigator
) : BasePresenter<TabContainerView>() {

    override fun onFirstViewAttach() {
        viewState.setFragment(TabContainerFragment.CHARACTERS)
    }

    fun onClickTab(keyFragment: String) {
        viewState.setFragment(keyFragment)
    }
}