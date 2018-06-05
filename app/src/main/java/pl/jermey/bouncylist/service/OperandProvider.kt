package pl.jermey.bouncylist.service

import android.support.annotation.IntRange
import java.util.*

interface OperandProvider {

    @IntRange(from = 0, to = 100)
    fun getOperation(): Int

    fun getArgument(): Int
}

class RandomOperandProvider : OperandProvider {

    private val random = Random()

    override fun getOperation(): Int {
        return Math.abs(random.nextInt(101))
    }

    override fun getArgument(): Int {
        return Math.abs(random.nextInt())
    }

}