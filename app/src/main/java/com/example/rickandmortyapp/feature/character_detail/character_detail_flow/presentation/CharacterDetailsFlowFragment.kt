package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation.CharacterDetailRouter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.model.CharacterModel
import com.example.rickandmortyapp.navigation.Command
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.navigation.NavigatorData
import com.example.rickandmortyapp.navigation.ScreenData
import java.util.*


class CharacterDetailsFlowFragment : BaseFlowFragment(), CharacterDetailsFlowView {

    @InjectPresenter
    lateinit var presenter: CharacterDetailsFlowPresenter
    lateinit var router: CharacterDetailRouter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsFlowPresenter::class.java)

    override fun getFragmentsList() = presenter.fragments

    override fun initRouter(fragmentsList: LinkedList<String>) {
        router = CharacterDetailRouter(childFragmentManager, R.id.fragmentContainerView, fragmentsList)
    }

    override fun chooseNavigationAction(navigatorData: NavigatorData) {
        var updatedNavigatorData = navigatorData
        if (navigatorData.screenData.screenName == Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL
            || navigatorData.command == Command.Navigate
        ) {
            val arg = arguments!!.getSerializable(key) as CharacterModel
            updatedNavigatorData = navigatorData.copy(   screenData = ScreenData(
                navigatorData.screenData.screenName,
                arg
            ))
        }
        router.chooseNavigation(updatedNavigatorData)

    }

    companion object {
        private const val key = "CharacterDetailsModel"

        fun getInstance(arg: CharacterModel): CharacterDetailsFlowFragment {
            return CharacterDetailsFlowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key, arg)
                }
            }
        }
    }

}