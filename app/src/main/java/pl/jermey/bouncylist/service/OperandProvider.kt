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

class TestOperandProvider : OperandProvider {

    private var argumentIndex = 0
    private var operationIndex = 0

    private val operations: Array<Int> = arrayOf(0, 0, 0, 0, 0, 1, 55, 88, 0, 99) // ...
    private val arguments: Array<Int> = arrayOf(0, 1, 0, 0, 1, 1, 3, 2, 0, 1) // ...

    override fun getOperation(): Int {
        if (operationIndex > operations.size) operationIndex = 0
        return operations[operationIndex++]
    }

    override fun getArgument(): Int {
        if (argumentIndex > arguments.size) argumentIndex = 0
        return arguments[argumentIndex++]
    }

}