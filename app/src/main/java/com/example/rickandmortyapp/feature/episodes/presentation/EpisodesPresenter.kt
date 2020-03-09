package com.example.rickandmortyapp.feature.episodes.presentation

import com.arellomobile.mvp.InjectViewState
import com.example.rickandmortyapp.core.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class EpisodesPresenter @Inject constructor(
) : BasePresenter<EpisodesView>() {

    override fun onFirstViewAttach() {

    }


}