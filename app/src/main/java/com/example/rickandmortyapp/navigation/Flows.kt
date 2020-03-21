package com.example.rickandmortyapp.navigation

import java.io.Serializable

abstract class Flow(val name: String) : Serializable

object Flows {

    object SPLASH : Flow("SPLASH_FLOW") {

        const val SPLASH = "SPLASH"

        val screens = listOf(SPLASH)
    }

    object TAB_CONTAINER : Flow("TAB_CONTAINER_FLOW") {

        const val TAB_CONTAINER = "TAB_CONTAINER"

        val screens = listOf(TAB_CONTAINER)
    }

    object  CHARACTER_DETAIL: Flow("CHARACTER_DETAIL_FLOW") {

        const val SCREEN_CHARACTER_DETAIL = "SCREEN_CHARACTER_DETAIL"

        val screens = listOf(SCREEN_CHARACTER_DETAIL)
    }

}
