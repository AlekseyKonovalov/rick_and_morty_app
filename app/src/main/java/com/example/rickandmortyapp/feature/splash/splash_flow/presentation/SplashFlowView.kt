package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import com.example.rickandmortyapp.core.view.Presentable
import com.example.rickandmortyapp.navigation.Navigation
import java.util.*

interface SplashFlowView: Presentable, Navigation {
    fun initRouter(fragmentsList: LinkedList<String>)
}