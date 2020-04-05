package com.example.rickandmortyapp.feature.tab_container.tab_container_fm.presentation

import com.arellomobile.mvp.MvpView

interface TabContainerView : MvpView {
    fun setFragment(keyFragment: String)
    fun initListeners()
}