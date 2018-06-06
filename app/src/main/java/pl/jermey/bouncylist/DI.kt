package pl.jermey.bouncylist

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import pl.jermey.bouncylist.service.*
import pl.jermey.bouncylist.util.ApplicationSchedulerProvider
import pl.jermey.bouncylist.util.SchedulerProvider
import pl.jermey.bouncylist.util.TestSchedulerProvider
import pl.jermey.bouncylist.viewmodel.MainViewModel

fun bouncyListModule(test: Boolean = false) = applicationContext {

    viewModel { MainViewModel(get(), get(), get()) }

    bean { AppTimerService(get()) as TimerService }
    bean { operandProvider(test) }
}

fun operandProvider(test: Boolean): OperandProvider {
    return if (test) TestOperandProvider() else RandomOperandProvider()
}

fun schedulerProvider(test: Boolean): SchedulerProvider {
    return if (test) TestSchedulerProvider() else ApplicationSchedulerProvider()
}

fun rxModule(test: Boolean = false) = applicationContext {
    bean { schedulerProvider(test) }
}

val appModules = listOf(bouncyListModule(), rxModule())
val testModules = listOf(bouncyListModule(true), rxModule(true))