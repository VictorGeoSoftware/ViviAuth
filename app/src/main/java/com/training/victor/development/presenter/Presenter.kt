package com.training.victor.development.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class Presenter<T1> {
    var view: T1? = null
    val compositeDisposable = CompositeDisposable()

    open fun destroy() {
        compositeDisposable.clear()
        view = null
    }
}