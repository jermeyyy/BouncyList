package pl.jermey.bouncylist.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.OvershootInterpolator
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.koin.android.architecture.ext.viewModel
import pl.jermey.bouncylist.R
import pl.jermey.bouncylist.data.TimerEntity
import pl.jermey.bouncylist.databinding.MainActivityBinding
import pl.jermey.bouncylist.viewmodel.ListEvent
import pl.jermey.bouncylist.viewmodel.MainViewModel
import pl.jermey.bouncylist.widget.TimerListItem


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: MainActivityBinding

    private val modelAdapter = ModelAdapter<TimerEntity, TimerListItem>({ TimerListItem(it) })
    private val adapter: FastAdapter<TimerListItem> = FastAdapter.with(listOf(modelAdapter))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))

        viewModel.listObservable.subscribe(::handleListEvent)
    }

    private fun handleListEvent(event: ListEvent) {
        when (event.type) {
            ListEvent.Type.ADD -> event.entity?.let { modelAdapter.add(it) }
            ListEvent.Type.DELETE -> modelAdapter.remove(event.position)
            ListEvent.Type.CHANGE -> adapter.notifyAdapterItemChanged(event.position)
        }
    }
}
