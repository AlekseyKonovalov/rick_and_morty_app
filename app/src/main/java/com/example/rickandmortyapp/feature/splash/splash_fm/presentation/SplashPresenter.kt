package com.example.rickandmortyapp.feature.splash.splash_fm.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.splash.splash_flow.navigation.SplashNavigator
import com.example.rickandmortyapp.navigation.*
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val splashNavigator: SplashNavigator
) : BasePresenter<SplashView>() {
    override fun onFirstViewAttach() {
        Observable.just("")
            .delay(1, TimeUnit.SECONDS)
            .compose(schedulersTransformerObservable())
            .subscribe(
                {
                    splashNavigator.emmitData(
                        NavigatorData(
                            Command.Replace,
                            ScreenData(
                                Flows.CHARACTERS.name
                            )
                        )
                    )
                },
                {
                    Timber.e(it.toString())
                }
            ).addToFullLifeCycle()
    }
}