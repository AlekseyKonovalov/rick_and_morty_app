package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.presentation

import com.example.rickandmortyapp.Presentable
import com.example.rickandmortyapp.navigation.Navigation
import com.example.rickandmortyapp.navigation.NavigatorData
import java.util.*

interface TabContainerFlowView : Presentable, Navigation {
    fun initRouter(fragmentsList: LinkedList<String>)
}