package com.maryang.fastrxjava.util.provider

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : SchedulerProviderInterface {
    override fun io() =
        Schedulers.io()

    override fun computation() =
        Schedulers.computation()

    override fun main() =
        AndroidSchedulers.mainThread()
}
