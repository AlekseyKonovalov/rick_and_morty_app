package com.example.rickandmortyapp.feature.characters.characters_flow.navigation

import androidx.fragment.app.FragmentManager
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.CharactersFragment
import com.example.rickandmortyapp.navigation.BaseRouter
import com.example.rickandmortyapp.navigation.Command
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

class CharactersRouter(
    private val fragmentManager: FragmentManager,
    private val fragmentContainerView: Int,
    private val fragments: LinkedList<String>
) : BaseRouter(fragmentManager, fragmentContainerView, fragments) {

    override fun chooseNavigation(navigatorData: NavigatorData) {
        val fragment: BaseFragment?
        val tag: String

        when (navigatorData.screenData.screenName) {
            Flows.CHARACTERS.CHARACTERS  -> {
                tag =  Flows.CHARACTERS.CHARACTERS
                fragment = if (navigatorData.command != Command.Remove) CharactersFragment.getInstance() else
                    fragmentManager.findFragmentByTag(tag) as BaseFlowFragment?
            }
            else -> return
        }

        if (fragment == null) return

        when (navigatorData.command) {
            Command.Navigate -> {
                navigateToScreen(fragment, tag)
            }
            Command.Replace -> {
                replaceScreen(fragment, tag)
            }
            Command.Remove -> {
                removeScreen()
            }
        }
    }
}