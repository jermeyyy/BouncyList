package pl.jermey.bouncylist.widget

import android.support.v7.widget.RecyclerView
import android.view.View
import pl.jermey.bouncylist.R
import pl.jermey.bouncylist.data.TimerEntity
import pl.jermey.bouncylist.databinding.TimerListItemBinding
import pl.jermey.bouncylist.util.KAbstractItem

class TimerListItem(private val timerEntity: TimerEntity)
    : KAbstractItem<TimerEntity, TimerListItem, TimerListItem.ViewHolder>(timerEntity, R.layout.timer_list_item, ::ViewHolder) {

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.binding.model = timerEntity
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: TimerListItemBinding = TimerListItemBinding.bind(itemView)
    }
}