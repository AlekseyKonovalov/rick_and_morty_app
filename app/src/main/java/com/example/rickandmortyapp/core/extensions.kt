package com.example.rickandmortyapp.core

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String.removeNonDigit() = replace("[^\\d]".toRegex(), "")