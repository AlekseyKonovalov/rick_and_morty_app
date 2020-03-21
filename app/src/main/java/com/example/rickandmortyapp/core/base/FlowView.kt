package com.example.rickandmortyapp.core.base

import com.arellomobile.mvp.MvpView

interface FlowView: MvpView {
    fun navigateToStartScreen(data: Any? = null)
}