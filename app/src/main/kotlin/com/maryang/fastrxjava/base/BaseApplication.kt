package com.maryang.fastrxjava.base

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.maryang.fastrxjava.event.LogoutEvent
import com.maryang.fastrxjava.event.RxBus
import com.maryang.fastrxjava.util.extension.showToast
import io.reactivex.android.schedulers.AndroidSchedulers

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
