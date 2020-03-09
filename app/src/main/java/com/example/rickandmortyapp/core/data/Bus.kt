package com.example.rickandmortyapp.core.data

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

open class Bus<T> {

    private val subject: Subject<T> = PublishSubject.create()

    fun getData() = subject

    fun emmitData(data: T) = subject.onNext(data)
}