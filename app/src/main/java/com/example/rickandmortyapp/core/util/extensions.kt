package com.example.rickandmortyapp.core.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.jakewharton.rxbinding2.view.RxView
import timber.log.Timber
import toothpick.Scope
import toothpick.config.Module
import java.util.concurrent.TimeUnit


fun Scope.module(func: Module.() -> Unit): Scope {
    installModules(com.example.rickandmortyapp.core.di.module { func(this) })
    return this
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun AppBarLayout.setTitle(collapsingToolbarLayout: CollapsingToolbarLayout, title: String) {
    var isShow = true
    var scrollRange = -1
    this.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
        if (scrollRange == -1) {
            scrollRange = barLayout?.totalScrollRange!!
        }
        if (scrollRange + verticalOffset == 0) {
            collapsingToolbarLayout.title = title
            isShow = true
        } else if (isShow) {
            collapsingToolbarLayout.title = " "
            isShow = false
        }
    })
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

fun Activity.hideKeyboard() {
    val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

const val CLICK_TIMEOUT_MILLISECONDS = 1000L