package com.example.rickandmortyapp.feature.tab_container.episodes.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.CharactersFragment

class EpisodesFragment : BaseFragment(), EpisodesView {
    override val layoutRes: Int = R.layout.episodes_fragment

    @InjectPresenter
    lateinit var presenter: EpisodesPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(EpisodesPresenter::class.java)

    companion object {
        fun getInstance(): EpisodesFragment {
            return EpisodesFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}