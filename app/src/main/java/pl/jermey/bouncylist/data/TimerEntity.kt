package pl.jermey.bouncylist.data

data class TimerEntity(var timer: String,
                       val color: TimerColor)

enum class TimerColor {
    BLUE, RED, GREEN, YELLOW
}