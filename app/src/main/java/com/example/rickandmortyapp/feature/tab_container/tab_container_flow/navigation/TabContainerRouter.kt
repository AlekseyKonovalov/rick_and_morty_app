package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation

import androidx.fragment.app.FragmentManager
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation.CharacterDetailsFlowFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.splash.splash_flow.presentation.SplashFlowFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation.TabContainerFlowFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_fm.presentation.TabContainerFragment
import com.example.rickandmortyapp.navigation.BaseRouter
import com.example.rickandmortyapp.navigation.Command
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

class TabContainerRouter(
    private val fragmentManager: FragmentManager,
    private val fragmentContainerView: Int,
    private val fragments: LinkedList<String>
) : BaseRouter(fragmentManager, fragmentContainerView, fragments) {

    override fun chooseNavigation(navigatorData: NavigatorData) {
        val fragment: BaseFragment?
        val tag: String

        when (navigatorData.screenData.screenName) {
            Flows.TAB_CONTAINER.TAB_CONTAINER  -> {
                tag =  Flows.TAB_CONTAINER.TAB_CONTAINER
                fragment = if (navigatorData.command != Command.Remove) TabContainerFragment.getInstance() else
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
                removeScreen(fragment)
            }
        }
    }
}