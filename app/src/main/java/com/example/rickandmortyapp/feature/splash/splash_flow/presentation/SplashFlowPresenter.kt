package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.navigation.AppNavigator
import com.example.rickandmortyapp.navigation.SplashNavigator
import javax.inject.Inject

@InjectViewState
class SplashFlowPresenter @Inject constructor(
       private val appNavigator: AppNavigator,
       private val splashNavigator: SplashNavigator
) : BasePresenter<SplashFlowView>() {
    override fun onFirstViewAttach() {
        viewState.navigateToStartScreen()

        splashNavigator.getData()
            .compose(schedulersTransformerObservable())
            .subscribe {
               if (Flows.CHARACTER_DETAIL.screens.contains(it.screenData.screenName)){

               } else {
                   appNavigator.emmitData(it)
               }
            }
            .addToFullLifeCycle()
    }
}