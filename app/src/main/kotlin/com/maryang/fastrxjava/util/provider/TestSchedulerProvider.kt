package com.maryang.fastrxjava.util.provider

import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProviderInterface {

    val testScheduler = TestScheduler()

    override fun io() =
        testScheduler

    override fun computation() =
        testScheduler

    override fun main() =
        testScheduler
}
