package com.example.rickandmortyapp.core.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    private val fullLifeCycleCompositeDisposable = CompositeDisposable()
    private val viewLifeCycleCompositeDisposable = CompositeDisposable()

    fun Disposable.addToFullLifeCycle() {
        fullLifeCycleCompositeDisposable.add(this)
    }

    fun Disposable.addToViewLifeCycle() {
        viewLifeCycleCompositeDisposable.add(this)
    }

    override fun onDestroy() {
        fullLifeCycleCompositeDisposable.dispose()
        viewLifeCycleCompositeDisposable.dispose()
    }

    protected fun <Stream> schedulersTransformerObservable(
        subscribeScheduler: Scheduler = Schedulers.io(),
        observeScheduler: Scheduler = AndroidSchedulers.mainThread()
    ): ObservableTransformer<Stream, Stream> {
        return ObservableTransformer { single ->
            single.subscribeOn(subscribeScheduler).observeOn(observeScheduler)
        }
    }


}