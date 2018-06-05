package pl.jermey.bouncylist.data

import android.databinding.BaseObservable

data class TimerEntity(var timer: Long,
                       val color: TimerColor) : BaseObservable()

enum class TimerColor {
    BLUE, RED
}