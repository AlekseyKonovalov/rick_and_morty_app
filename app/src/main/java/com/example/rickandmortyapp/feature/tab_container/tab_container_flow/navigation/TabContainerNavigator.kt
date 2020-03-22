package com.example.rickandmortyapp.feature.tab_container.tab_container_flow.navigation

import com.example.rickandmortyapp.core.data.Bus
import com.example.rickandmortyapp.navigation.NavigatorData
import javax.inject.Inject

class TabContainerNavigator  @Inject constructor() : Bus<NavigatorData>() {}