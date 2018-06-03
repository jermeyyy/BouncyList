package pl.jermey.bouncylist

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import pl.jermey.bouncylist.util.ApplicationSchedulerProvider
import pl.jermey.bouncylist.util.TestSchedulerProvider
import pl.jermey.bouncylist.viewmodel.MainViewModel

fun bouncyListModule(test: Boolean = false) = applicationContext {

    viewModel { MainViewModel() }

//    bean { AppTimerService() as TimerService }
}

fun rxModule(test: Boolean = false) = applicationContext {
    bean { if (test) TestSchedulerProvider() else ApplicationSchedulerProvider() }
}

val appModules = listOf(bouncyListModule(), rxModule())
val testModules = listOf(bouncyListModule(), rxModule(true))