package com.example.rickandmortyapp.feature.character_detail.character_detail_flow.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharacterDetailsFlowView : Presentable {
    fun initRouter(fragmentsList: LinkedList<String>)
    fun navigateToScreen(navigatorData: NavigatorData)
}