package com.example.rickandmortyapp

import com.example.rickandmortyapp.core.data.Bus
import javax.inject.Inject

class Navigator @Inject constructor() : Bus<ScreenData>() {
}