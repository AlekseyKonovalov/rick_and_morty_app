package com.example.rickandmortyapp.feature.location.presentation

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.core.base.BaseFragment

class LocationFragment : BaseFragment(), LocationView {
    override val layoutRes: Int = R.layout.location_fragment

    @InjectPresenter
    lateinit var presenter: LocationPresenter

    @ProvidePresenter
    fun providePresenter() = scope.getInstance(LocationPresenter::class.java)

}