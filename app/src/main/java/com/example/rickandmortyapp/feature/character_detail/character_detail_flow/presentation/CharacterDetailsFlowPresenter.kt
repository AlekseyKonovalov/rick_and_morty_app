package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation.CharacterDetailNavigator
import com.example.rickandmortyapp.navigation.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@InjectViewState
class CharacterDetailsFlowPresenter @Inject constructor(
    private val appNavigator: AppNavigator,
    private val characterDetailNavigator: CharacterDetailNavigator
) : BasePresenter<CharacterDetailsFlowView>() {

    var fragments: LinkedList<String> = LinkedList()

    override fun onFirstViewAttach() {
        viewState.initRouter(fragments)
        viewState.chooseNavigationAction(
            NavigatorData(
                Command.Navigate,
                ScreenData(Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL)
            )
        )

        characterDetailNavigator.getData()
            .compose(schedulersTransformerObservable())
            .subscribe({
                if (Flows.CHARACTER_DETAIL.screens.contains(it.screenData.screenName)) {
                    viewState.chooseNavigationAction(it)
                } else {
                    appNavigator.emmitData(it)
                }
            }, {
                Timber.e(it.toString())
            })
            .addToFullLifeCycle()
    }

    fun onBackPressed() {
        appNavigator.emmitData(NavigatorData(Command.Remove, ScreenData(Flows.CHARACTER_DETAIL.name)))
    }

}