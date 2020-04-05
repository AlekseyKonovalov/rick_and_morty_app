package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation.TabContainerRouter
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

class TabContainerFlowFragment : BaseFlowFragment(), TabContainerFlowView {

    @InjectPresenter
    lateinit var presenter: TabContainerFlowPresenter
    lateinit var router: TabContainerRouter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(TabContainerFlowPresenter::class.java)

    override fun getFragmentsList() = presenter.fragments

    override fun initRouter(fragmentsList: LinkedList<String>) {
        router = TabContainerRouter(fragmentManager!!, R.id.fragmentContainerView, fragmentsList)
    }

    override fun chooseNavigationAction(navigatorData: NavigatorData) {
        router.chooseNavigation(navigatorData)
    }


}