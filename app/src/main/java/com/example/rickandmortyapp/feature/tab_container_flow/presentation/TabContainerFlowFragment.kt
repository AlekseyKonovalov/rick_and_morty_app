package com.example.rickandmortyapp.feature.tab_container_flow.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.characters.presentation.model.CharacterFavoriteBus
import com.example.rickandmortyapp.feature.tab_container.presentation.TabContainerFragment
import toothpick.Scope
import toothpick.config.Module

class TabContainerFlowFragment : BaseFragment(),
    TabContainerFlowView {

    override val layoutRes: Int = R.layout.fragment_container_view

    @InjectPresenter
    lateinit var presenter: TabContainerFlowPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(TabContainerFlowPresenter::class.java)

    override fun navigateToStartScreen() {
        fragmentManager?.beginTransaction()
            ?.add(
                R.id.fragmentContainerView,
                TabContainerFragment()
            )
            ?.commit()
    }
}