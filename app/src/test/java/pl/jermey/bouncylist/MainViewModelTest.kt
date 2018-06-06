package pl.jermey.bouncylist

import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import pl.jermey.bouncylist.data.TimerColor
import pl.jermey.bouncylist.data.TimerEntity
import pl.jermey.bouncylist.viewmodel.ListEvent
import pl.jermey.bouncylist.viewmodel.MainViewModel
import java.util.concurrent.TimeUnit

class MainViewModelTest : KoinTest {

    private val mainViewModel: MainViewModel by inject()

    @Before
    fun before() {
        StandAloneContext.startKoin(testModules)
    }

    @After
    fun after() {
        StandAloneContext.closeKoin()
    }

    @Test
    fun testDispatchTimer() {
        val testObserver = mainViewModel.listObservable.test()
        Observable.intervalRange(0, 10, 0, 10, TimeUnit.MILLISECONDS)
                .subscribe { mainViewModel.dispatchTimer(it) }

        testObserver.awaitCount(10)
        testObserver.assertValueSequence(arrayListOf(
                ListEvent(ListEvent.Type.ADD, 0, TimerEntity(0, TimerColor.BLUE)),
                ListEvent(ListEvent.Type.ADD, 1, TimerEntity(2, TimerColor.RED)),
                ListEvent(ListEvent.Type.ADD, 2, TimerEntity(2, TimerColor.BLUE)),
                ListEvent(ListEvent.Type.ADD, 3, TimerEntity(0, TimerColor.BLUE)),
                ListEvent(ListEvent.Type.ADD, 4, TimerEntity(4, TimerColor.RED)),
                ListEvent(ListEvent.Type.CHANGE, 1),
                ListEvent(ListEvent.Type.CHANGE, 3),
                ListEvent(ListEvent.Type.DELETE, 2),
                ListEvent(ListEvent.Type.ADD, 4, TimerEntity(8, TimerColor.BLUE)),
                ListEvent(ListEvent.Type.CHANGE, 1)
        ))
        testObserver.assertNoErrors()
    }
}
