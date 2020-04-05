package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.splash.splash_flow.navigation.SplashNavigator
import com.example.rickandmortyapp.navigation.*
import java.util.*
import javax.inject.Inject

@InjectViewState
class SplashFlowPresenter @Inject constructor(
       private val appNavigator: AppNavigator,
       private val splashNavigator: SplashNavigator
) : BasePresenter<SplashFlowView>() {

    var fragments: LinkedList<String> = LinkedList()

    override fun onFirstViewAttach() {
        viewState.initRouter(fragments)
        viewState.chooseNavigationAction(NavigatorData(Command.Navigate, ScreenData(Flows.SPLASH.SPLASH)))

        splashNavigator.getData()
            .compose(schedulersTransformerObservable())
            .subscribe {
               if (Flows.SPLASH.screens.contains(it.screenData.screenName)){

               } else {
                   appNavigator.emmitData(it)
               }
            }
            .addToFullLifeCycle()
    }
}