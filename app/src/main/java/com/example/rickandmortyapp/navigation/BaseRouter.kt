package com.example.rickandmortyapp.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rickandmortyapp.core.base.BaseFragment
import java.util.*

abstract class BaseRouter(
    private val fragmentManager: FragmentManager,
    private val fragmentContainerView: Int,
    private val fragments: LinkedList<String>
) {

    abstract fun chooseNavigation(navigatorData: NavigatorData)

    protected fun navigateToScreen(
        fragment: BaseFragment,
        tag: String
    ) {
        fragments.add(tag)
        fragmentManager.beginTransaction()
            .add(
                fragmentContainerView,
                fragment,
                tag
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }


    protected fun removeScreen() {
        fragments.removeLast()
        fragmentManager.popBackStack()
    }

    protected fun replaceScreen(
        fragment: BaseFragment,
        tag: String
    ) {
        fragments.removeLast()
        fragments.add(tag)
        fragmentManager.beginTransaction()
            .replace(
                fragmentContainerView,
                fragment,
                tag
            )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}
