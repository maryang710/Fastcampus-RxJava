package com.maryang.fastrxjava.observer

import com.maryang.fastrxjava.util.ErrorHandler
import io.reactivex.observers.DisposableSingleObserver


abstract class DefaultSingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onError(e: Throwable) {
        ErrorHandler.handle(e)
    }
}
