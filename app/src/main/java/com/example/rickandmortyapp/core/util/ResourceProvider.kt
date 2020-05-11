package com.example.rickandmortyapp.core.util

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject


class ResourceProvider @Inject constructor(private val context: Context) {
    fun getString(@StringRes id: Int) = context.getString(id)
}