package com.example.rickandmortyapp.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.jakewharton.rxbinding2.view.RxView
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.handleDoubleClick(
    timeout: Long = CLICK_TIMEOUT_MILLISECONDS,
    onClickListener: () -> Unit
) {
    RxView.clicks(this)
        .throttleFirst(timeout, TimeUnit.MILLISECONDS)
        .subscribe({ onClickListener.invoke() }, { Timber.e(it.toString()) })
}

fun Context.inflate(
    @LayoutRes resource: Int, root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(resource, root, attachToRoot)
}

fun View.inflate(
    @LayoutRes resource: Int, root: ViewGroup? = null,
    attachToRoot: Boolean = false
): View {
    return context.inflate(resource, root, attachToRoot)
}

fun String.removeNonDigit() = replace("[^\\d]".toRegex(), "")

const val CLICK_TIMEOUT_MILLISECONDS = 1000L