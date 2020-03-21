package com.example.rickandmortyapp.navigation

import com.example.rickandmortyapp.core.data.Bus
import javax.inject.Inject

class TabContainerNavigator  @Inject constructor() : Bus<NavigatorData>() {}