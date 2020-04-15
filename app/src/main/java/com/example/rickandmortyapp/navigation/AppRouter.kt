package com.example.rickandmortyapp.navigation

import androidx.fragment.app.FragmentManager
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation.CharacterDetailsFlowFragment
import com.example.rickandmortyapp.feature.splash.splash_flow.presentation.SplashFlowFragment
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.feature.characters.characters_flow.presentation.CharactersFlowFragment
import java.util.*

class AppRouter(
    private val fragmentManager: FragmentManager,
    private val fragmentContainerView: Int,
    private val fragments: LinkedList<String>
) : BaseRouter(fragmentManager, fragmentContainerView, fragments) {

    override fun chooseNavigation(navigatorData: NavigatorData) {
        val flowFragment: BaseFlowFragment?
        val tag: String

        when (navigatorData.screenData.screenName) {
            Flows.SPLASH.name -> {
                tag = Flows.SPLASH.name
                flowFragment = if (navigatorData.command != Command.Remove) SplashFlowFragment.getInstance() else
                    fragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            Flows.CHARACTERS.name -> {
                tag = Flows.CHARACTERS.name
                flowFragment = if (navigatorData.command != Command.Remove) CharactersFlowFragment() else
                    fragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            Flows.CHARACTER_DETAIL.name -> {
                tag = Flows.CHARACTER_DETAIL.name
                flowFragment =
                    if (navigatorData.command != Command.Remove) CharacterDetailsFlowFragment.getInstance(navigatorData.screenData.data as CharacterModel)
                    else fragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            else -> return
        }

        if (flowFragment == null) return

        when (navigatorData.command) {
            Command.Navigate -> {
                navigateToScreen(flowFragment, tag)
            }
            Command.Replace -> {
                replaceScreen(flowFragment, tag)
            }
            Command.Remove -> {
                removeScreen()
            }
        }
    }
}