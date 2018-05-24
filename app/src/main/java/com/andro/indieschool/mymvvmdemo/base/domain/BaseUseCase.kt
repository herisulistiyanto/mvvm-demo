package com.andro.indieschool.mymvvmdemo.base.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase<T>(
        private val disposables: CompositeDisposable = CompositeDisposable(),
        val liveData: MutableLiveData<T> = MutableLiveData()) : UseCase<T> {

    protected fun Disposable.track() {
        disposables.add(this)
    }

    override fun getLiveData(): LiveData<T> = liveData

    override fun cleanUp() {
        disposables.clear()
    }
}