package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation

import androidx.fragment.app.FragmentManager
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation.CharacterDetailsFragment
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.model.CharacterModel
import com.example.rickandmortyapp.navigation.BaseRouter
import com.example.rickandmortyapp.navigation.Command
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

class CharacterDetailRouter(
    private val fragmentManager: FragmentManager,
    private val fragmentContainerView: Int,
    private val fragments: LinkedList<String>
) : BaseRouter(fragmentManager, fragmentContainerView, fragments) {

    override fun chooseNavigation(navigatorData: NavigatorData) {
        val fragment: BaseFragment?
        val tag: String

        when (navigatorData.screenData.screenName) {
            Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL  -> {
                tag =  Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL
                fragment = if (navigatorData.command != Command.Remove)
                    CharacterDetailsFragment.getInstance(navigatorData.screenData.data as CharacterModel) else
                    fragmentManager.findFragmentByTag(tag) as BaseFragment?
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