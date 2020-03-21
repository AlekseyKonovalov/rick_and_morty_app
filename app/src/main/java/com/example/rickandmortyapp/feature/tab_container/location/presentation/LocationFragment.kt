package com.example.rickandmortyapp.feature.tab_container.location.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment
import com.example.rickandmortyapp.feature.tab_container.characters.presentation.CharactersFragment

class LocationFragment : BaseFragment(), LocationView {
    override val layoutRes: Int = R.layout.location_fragment

    @InjectPresenter
    lateinit var presenter: LocationPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(LocationPresenter::class.java)

    companion object {
        fun getInstance(): LocationFragment {
            return LocationFragment().apply {
                arguments = Bundle().apply {

                }
            }
        }
    }

}