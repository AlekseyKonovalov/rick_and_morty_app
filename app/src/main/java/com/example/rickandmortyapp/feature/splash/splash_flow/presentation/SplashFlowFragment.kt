package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.splash.splash_flow.navigation.SplashRouter
import com.example.rickandmortyapp.feature.splash.splash_fm.presentation.SplashFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation.TabContainerRouter
import com.example.rickandmortyapp.navigation.Flows
import com.example.rickandmortyapp.navigation.NavigatorData
import kotlinx.android.synthetic.main.character_details_fragment.*
import java.util.*

class SplashFlowFragment : BaseFlowFragment(), SplashFlowView {

    @InjectPresenter
    lateinit var presenter: SplashFlowPresenter
    lateinit var router: SplashRouter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(SplashFlowPresenter::class.java)

    override fun getFragmentsList(): LinkedList<String> = presenter.fragments

    override fun initRouter(fragmentsList: LinkedList<String>) {
        router = SplashRouter(fragmentManager!!, R.id.fragmentContainerView, fragmentsList)
    }

    override fun chooseNavigationAction(navigatorData: NavigatorData) {
        router.chooseNavigation(navigatorData)
    }

    companion object {
        fun getInstance(): SplashFlowFragment {
            return SplashFlowFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}