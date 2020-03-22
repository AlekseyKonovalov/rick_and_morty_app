package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.character_detail.character_detail_flow.navigation.CharacterDetailRouter
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.model.CharacterDetailsModel
import com.example.rickandmortyapp.feature.character_detail.character_detail_fm.presentation.CharacterDetailsFragment
import com.example.rickandmortyapp.feature.splash.splash_fm.presentation.SplashFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation.TabContainerRouter
import com.example.rickandmortyapp.navigation.Command
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.navigation.NavigatorData
import com.example.rickandmortyapp.navigation.ScreenData
import kotlinx.android.synthetic.main.character_details_fragment.*
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind
import java.util.*


class CharacterDetailsFlowFragment : BaseFlowFragment(), CharacterDetailsFlowView {

    @InjectPresenter
    lateinit var presenter: CharacterDetailsFlowPresenter
    lateinit var router: CharacterDetailRouter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharacterDetailsFlowPresenter::class.java)

    override fun getFragmentsList() = presenter.fragments

    override fun initRouter(fragmentsList: LinkedList<String>) {
        router = CharacterDetailRouter(fragmentManager!!, R.id.fragmentContainerView, fragmentsList)
    }

    override fun navigateToScreen(navigatorData: NavigatorData) {
        // todo refactor
        if (navigatorData.screenData.screenName == Flows.CHARACTER_DETAIL.SCREEN_CHARACTER_DETAIL
            || navigatorData.command == Command.Navigate
        ) {
            val arg = arguments!!.getSerializable(key) as CharacterDetailsModel
            router.chooseNavigation(
                navigatorData.copy(
                    screenData = ScreenData(
                        navigatorData.screenData.screenName,
                        arg
                    )
                )
            )
        } else if (navigatorData.screenData.screenName == presenter.fragments.last() && navigatorData.command == Command.Remove) {
            router.chooseNavigation(navigatorData)
            presenter.onBackPressed()

        } else {
            router.chooseNavigation(navigatorData)
        }

    }

    companion object {
        private const val key = "CharacterDetailsModel"

        fun getInstance(arg: CharacterDetailsModel): CharacterDetailsFlowFragment {
            return CharacterDetailsFlowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(key, arg)
                }
            }
        }
    }

}