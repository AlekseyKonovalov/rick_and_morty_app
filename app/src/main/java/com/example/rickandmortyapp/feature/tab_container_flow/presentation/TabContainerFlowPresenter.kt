package com.example.rickandmortyapp.feature.tab_container_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.Navigator
import com.example.rickandmortyapp.core.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class TabContainerFlowPresenter @Inject constructor(
    private val navigator: Navigator
) : BasePresenter<TabContainerFlowView>() {
    override fun onFirstViewAttach() {
        viewState.navigateToStartScreen()
        navigator.getData()
            .compose(schedulersTransformerObservable())
            .subscribe {
                viewState.navigateToScreen(it.screenName, it.data)
            }
            .addToFullLifeCycle()
    }
}