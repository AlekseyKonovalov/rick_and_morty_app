package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation

import com.arellomobile.mvp.MvpView
import com.example.rickandmortyapp.core.base.FlowView

interface TabContainerFlowView : FlowView {
    fun navigateToScreen(screen: String, data: Any?)
}