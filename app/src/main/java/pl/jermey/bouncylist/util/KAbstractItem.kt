package pl.jermey.bouncylist.util

import android.annotation.SuppressLint
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.IClickable
import com.mikepenz.fastadapter.items.ModelAbstractItem

@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")
open class KAbstractItem<Model, Item, VH>(
        private val model: Model,
        @param:LayoutRes private val layoutRes: Int,
        private val viewHolder: (v: View) -> VH,
        private val type: Int = layoutRes
) : ModelAbstractItem<Model, Item, VH>(model)
        where Item : ModelAbstractItem<Model, Item, VH>,
              Item : IClickable<Item>,
              VH : RecyclerView.ViewHolder {
    @SuppressLint("ResourceType")
    override fun getType(): Int = type

    override fun getViewHolder(v: View): VH = viewHolder(v)

    override fun getLayoutRes(): Int = layoutRes

    override fun getModel(): Model = model
}