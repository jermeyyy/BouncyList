package pl.jermey.bouncylist.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter.commons.utils.FastAdapterDiffUtil
import org.koin.android.architecture.ext.viewModel
import pl.jermey.bouncylist.R
import pl.jermey.bouncylist.databinding.MainActivityBinding
import pl.jermey.bouncylist.viewmodel.MainViewModel
import pl.jermey.bouncylist.widget.TimerListItem

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: MainActivityBinding

    private val adapter: FastItemAdapter<TimerListItem> = FastItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.viewModel = viewModel
        binding.adapter = FastAdapterDiffUtil.set(adapter, viewModel.timerList.map { TimerListItem(it) })
        viewModel.viewContract.subscribe({ event ->
            val diff = FastAdapterDiffUtil.calculateDiff(adapter, viewModel.timerList.map { TimerListItem(it) })
            FastAdapterDiffUtil.set(adapter, diff)
        })
    }
}
