package com.example.rickandmortyapp.feature.characters.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment

class CharactersFragment : BaseFragment(), CharactersView {
    override val layoutRes: Int = R.layout.characters_fragment

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(CharactersPresenter::class.java)

}