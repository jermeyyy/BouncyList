package pl.jermey.bouncylist.service

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import pl.jermey.bouncylist.util.SchedulerProvider
import pl.jermey.bouncylist.util.scheduleComputation
import java.util.concurrent.TimeUnit

interface TimerService {
    val tickSubject: PublishSubject<Long>
    fun startTimer()
    fun stopTimer()
    fun dispose()
}

class AppTimerService(private val schedulerProvider: SchedulerProvider) : TimerService {

    private var currentTick: Long = 0L
    private var subscriptions: CompositeDisposable = CompositeDisposable()
    override val tickSubject: PublishSubject<Long> = PublishSubject.create()

    override fun startTimer() {
        stopTimer()
        val subscription = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .scheduleComputation(schedulerProvider)
                .subscribe({
                    Log.d("AppTimerService", "tick:$currentTick")
                    tickSubject.onNext(currentTick)
                    currentTick++
                }, { it.printStackTrace() })
        subscriptions = CompositeDisposable(subscription)
    }

    override fun stopTimer() {
        subscriptions.dispose()
        currentTick = 0L
    }

    override fun dispose() {
        subscriptions.dispose()
    }

}