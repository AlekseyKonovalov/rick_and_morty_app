package com.example.rickandmortyapp.navigation

import java.io.Serializable

abstract class Flow(val name: String) : Serializable

object Flows {

    object SPLASH : Flow("SPLASH_FLOW") {

        const val SPLASH = "SPLASH"

        val screens = listOf(SPLASH)
    }

    object CHARACTERS : Flow("CHARACTERS") {

        const val CHARACTERS = "CHARACTERS"

        val screens = listOf(CHARACTERS)
    }

    object  CHARACTER_DETAIL: Flow("CHARACTER_DETAIL_FLOW") {

        const val SCREEN_CHARACTER_DETAIL = "SCREEN_CHARACTER_DETAIL"

        val screens = listOf(SCREEN_CHARACTER_DETAIL)
    }

}
