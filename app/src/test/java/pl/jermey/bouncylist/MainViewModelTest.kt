package pl.jermey.bouncylist

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.jermey.bouncylist.viewmodel.MainViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {
    @Test
    fun addition_isCorrect() {
        val viewModel = MainViewModel()
        assertEquals(4, 2 + 2)
    }
}
