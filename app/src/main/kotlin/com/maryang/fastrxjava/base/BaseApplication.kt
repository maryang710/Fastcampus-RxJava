package com.maryang.fastrxjava.base

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.maryang.fastrxjava.event.LogoutEvent
import com.maryang.fastrxjava.event.RxBus
import com.maryang.fastrxjava.util.ErrorHandler
import com.maryang.fastrxjava.util.extension.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins

class BaseApplication : Application() {

    companion object {
        lateinit var appContext: Context
        const val TAG = "FastRxJava2"
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Stetho.initializeWithDefaults(this)
        setErrorHanlder()
    }

    private fun setErrorHanlder() {
        // onError 가 없거나, onError에서 또 Exception이 나면 오는애
        RxJavaPlugins.setErrorHandler {
            ErrorHandler.handle(it)
        }

        RxBus.observe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is LogoutEvent ->
                        showToast("Logout")
                }
            }
    }
}
