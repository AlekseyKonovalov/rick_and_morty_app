package com.example.rickandmortyapp.navigation

data class NavigatorData (
    val command: Command,
    val screenData: ScreenData
)

enum class Command {
    Navigate,
    Replace
}