package com.example.rickandmortyapp.core.di

import toothpick.config.Module

fun module(func: (Module.() -> (Unit))) = object : Module() {
    init {
        func()
    }
}
