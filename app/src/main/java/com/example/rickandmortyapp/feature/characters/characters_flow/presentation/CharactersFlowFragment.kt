package com.example.rickandmortyapp.feature.characters.characters_flow.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFlowFragment
import com.example.rickandmortyapp.core.module
import com.example.rickandmortyapp.feature.characters.characters_flow.navigation.CharactersRouter
import com.example.rickandmortyapp.feature.characters.characters_fm.presentation.filter_dialog.model.FilterDataBus
import com.example.rickandmortyapp.navigation.NavigatorData
import toothpick.Toothpick
import java.util.*

class CharactersFlowFragment : BaseFlowFragment(), CharactersFlowView {

    @InjectPresenter
    lateinit var presenter: CharactersFlowPresenter
    lateinit var router: CharactersRouter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharactersFlowPresenter::class.java)

    override fun getFragmentsList() = presenter.fragments

    override fun initRouter(fragmentsList: LinkedList<String>) {
        router = CharactersRouter(childFragmentManager, R.id.fragmentContainerView, fragmentsList)
    }

    override fun chooseNavigationAction(navigatorData: NavigatorData) {
        router.chooseNavigation(navigatorData)
    }

    override fun injectDependencies() {
        scope.module {
            bind(FilterDataBus::class.java).singleton()
        }.inject(this)
    }


}