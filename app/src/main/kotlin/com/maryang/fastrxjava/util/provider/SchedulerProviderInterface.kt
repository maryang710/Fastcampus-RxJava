package com.maryang.fastrxjava.util.provider

import io.reactivex.Scheduler

interface SchedulerProviderInterface {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
}
