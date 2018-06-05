package pl.jermey.bouncylist.util

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApplicationSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()
}

class TestSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()
}

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}

inline fun <reified T> Observable<T>.scheduleIO(schedulerProvider: SchedulerProvider): Observable<T> = this.subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())

inline fun <reified T> Observable<T>.scheduleComputation(schedulerProvider: SchedulerProvider): Observable<T> = this.subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())