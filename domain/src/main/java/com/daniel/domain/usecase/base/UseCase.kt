package com.daniel.domain.usecase.base

import com.app.daniel.domain.usecase.base.IUseCase
import com.daniel.common.applyScheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase<T> : IUseCase {

    abstract fun buildUseCase():Single<T>


    override var disposable: Disposable?
        get() = null
        set(value) {}

    override val compositeDisposable: CompositeDisposable
        get() = CompositeDisposable()

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t:Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ){
        dispose()
        disposable = buildUseCase()
            .applyScheduler()
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess,onError)
        disposable.let { disposable ->
            if (disposable != null) {
                compositeDisposable.add(disposable)
            }
        }
    }

    override fun dispose() {
        disposable?.let{ disposable ->
            if(!disposable.isDisposed) {
                disposable.dispose()
            }
        }
    }

    override fun disposeAll() {
        compositeDisposable.clear()
    }

}