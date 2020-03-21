package com.example.rickandmortyapp.navigation

import com.example.rickandmortyapp.core.data.Bus
import javax.inject.Inject

class AppNavigator @Inject constructor() : Bus<NavigatorData>() {}