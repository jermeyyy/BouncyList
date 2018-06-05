package pl.jermey.bouncylist.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import pl.jermey.bouncylist.data.TimerColor
import pl.jermey.bouncylist.data.TimerEntity
import pl.jermey.bouncylist.service.OperandProvider
import pl.jermey.bouncylist.service.TimerService
import pl.jermey.bouncylist.util.SchedulerProvider
import pl.jermey.bouncylist.util.scheduleComputation
import java.util.*

class MainViewModel(private val timerService: TimerService, private val operandProvider: OperandProvider, private val schedulerProvider: SchedulerProvider) : ViewModel() {

    private val listEventSubject: PublishSubject<ListEvent> = PublishSubject.create()
    private val timerList: ArrayList<TimerEntity> = ArrayList()
    private val timerSubscription: Disposable

    val listObservable: Observable<ListEvent> = listEventSubject.scheduleComputation(schedulerProvider)

    init {
        timerSubscription = timerService.tickSubject.subscribe({ dispatchTimer(it) }, { it.printStackTrace() })
    }

    fun dispatchTimer(currentTick: Long) {
        val argument = operandProvider.getArgument()
        val operation = operandProvider.getOperation()
        if (timerList.size < 5) {
            val entity = TimerEntity(currentTick, if (argument % 2 == 0) TimerColor.BLUE else TimerColor.RED)
            timerList.add(entity)
            listEventSubject.onNext(ListEvent(ListEvent.Type.ADD, timerList.size - 1, entity))
        } else {
            val position = argument % timerList.size
            when (operation) {
                in 0..49 -> {
                    timerList[position].timer++
                    listEventSubject.onNext(ListEvent(ListEvent.Type.CHANGE, position))
                }
                in 50..85 -> {
                    timerList[position].timer = 0
                    listEventSubject.onNext(ListEvent(ListEvent.Type.CHANGE, position))
                }
                in 86..95 -> {
                    timerList.removeAt(position)
                    listEventSubject.onNext(ListEvent(ListEvent.Type.DELETE, position))
                }
                in 96..100 -> {
                    val previousIndex = if (position == 0) timerList.size - 1 else position - 1
                    timerList[position].timer += timerList[previousIndex].timer
                    listEventSubject.onNext(ListEvent(ListEvent.Type.CHANGE, position))
                }
                else -> throw RuntimeException("Operation out of range")
            }
        }
    }

    fun startTimer() {
        timerService.startTimer()
    }

    fun stopTimer() {
        timerService.stopTimer()
    }

    override fun onCleared() {
        super.onCleared()
        timerSubscription.dispose()
        timerService.dispose()
    }
}

data class ListEvent(val type: Type,
                     val position: Int,
                     val entity: TimerEntity? = null) {
    enum class Type {
        ADD, DELETE, CHANGE
    }
}