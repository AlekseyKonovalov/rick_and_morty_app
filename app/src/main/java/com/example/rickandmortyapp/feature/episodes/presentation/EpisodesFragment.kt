package com.example.rickandmortyapp.feature.episodes.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment

class EpisodesFragment : BaseFragment(), EpisodesView {
    override val layoutRes: Int = R.layout.episodes_fragment

    @InjectPresenter
    lateinit var presenter: EpisodesPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(EpisodesPresenter::class.java)

}