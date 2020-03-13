package com.example.rickandmortyapp.feature.tab_container_flow.presentation

import com.arellomobile.mvp.MvpView

interface TabContainerFlowView : MvpView {
    fun navigateToStartScreen()
    fun navigateToScreen(screen: String, data: Any)
}