package com.example.rickandmortyapp.feature.splash.splash_flow.presentation

import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

interface SplashFlowView: Presentable {
    fun initRouter(fragmentsList: LinkedList<String>)
    fun navigateToScreen(navigatorData: NavigatorData)
}