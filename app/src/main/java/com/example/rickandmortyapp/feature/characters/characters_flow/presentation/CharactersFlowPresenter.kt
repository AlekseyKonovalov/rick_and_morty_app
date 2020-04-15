package com.example.rickandmortyapp.feature.characters.characters_flow.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersNavigator
import com.example.rickandmortyapp.navigation.*
import java.util.*
import javax.inject.Inject

@InjectViewState
class CharactersFlowPresenter @Inject constructor(
    private val appNavigator: AppNavigator,
    private val charactersNavigator: CharactersNavigator
) : BasePresenter<CharactersFlowView>() {

    var fragments: LinkedList<String> = LinkedList()

    override fun onFirstViewAttach() {
        viewState.initRouter(fragments)
        viewState.chooseNavigationAction(NavigatorData(Command.Navigate, ScreenData(Flows.CHARACTERS.CHARACTERS)))
    }

}