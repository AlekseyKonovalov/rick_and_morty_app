package com.example.rickandmortyapp.feature.splash.splash_flow.navigation

import com.example.rickandmortyapp.core.data.Bus
import com.example.rickandmortyapp.navigation.NavigatorData
import javax.inject.Inject

class SplashNavigator @Inject constructor() : Bus<NavigatorData>() {}