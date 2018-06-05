package pl.jermey.bouncylist

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import pl.jermey.bouncylist.viewmodel.MainViewModel

class MainViewModelTest : KoinTest {

    @Before
    fun before() {
        StandAloneContext.startKoin(testModules)
    }

    @After
    fun after() {
        StandAloneContext.closeKoin()
    }

    @Test
    fun addition_isCorrect() {
        val viewModel = MainViewModel()
        val testObserver = viewModel.listEventSubject.test()
        viewModel.dispatchTimer(it)
        testObserver.assertNoErrors()
        assertEquals(4, 2 + 2)
    }
}
