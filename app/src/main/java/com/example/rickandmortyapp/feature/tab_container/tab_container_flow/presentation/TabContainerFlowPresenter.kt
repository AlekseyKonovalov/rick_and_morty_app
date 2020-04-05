package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation.TabContainerNavigator
import com.example.rickandmortyapp.navigation.*
import java.util.*
import javax.inject.Inject

@InjectViewState
class TabContainerFlowPresenter @Inject constructor(
    private val appNavigator: AppNavigator,
    private val tabContainerNavigator: TabContainerNavigator
) : BasePresenter<TabContainerFlowView>() {

    var fragments: LinkedList<String> = LinkedList()

    override fun onFirstViewAttach() {
        viewState.initRouter(fragments)
        viewState.chooseNavigationAction(NavigatorData(Command.Navigate, ScreenData(Flows.TAB_CONTAINER.TAB_CONTAINER)))
    }

}