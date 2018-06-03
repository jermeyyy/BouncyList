package pl.jermey.bouncylist.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.subjects.PublishSubject
import pl.jermey.bouncylist.data.TimerColor
import pl.jermey.bouncylist.data.TimerEntity

class MainViewModel/* ( private val timerService: timerService ) */ : ViewModel() {

    val viewContract: PublishSubject<ListEvent> = PublishSubject.create()

    var timerList: List<TimerEntity> = emptyList()

    fun dispatchTimer() {
        timerList + TimerEntity("", TimerColor.BLUE)
        viewContract.onNext(ListEvent(ListEvent.Type.ADD))
    }

    fun startTime() {

    }

    fun stopTimer() {

    }
}

data class ListEvent(val type: Type) {
    enum class Type {
        ADD, DELETE
    }
}