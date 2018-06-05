package pl.jermey.bouncylist.util

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import pl.jermey.bouncylist.R
import pl.jermey.bouncylist.data.TimerColor


object DataBinding {
    @JvmStatic
    @BindingAdapter("timerColor")
    fun setTimerColor(v: ImageView, timerColor: TimerColor) {
        v.setImageResource(if (timerColor == TimerColor.BLUE) R.drawable.blue_dot else R.drawable.red_dot)
    }

    @JvmStatic
    @BindingAdapter("timerValue")
    fun setTimerColor(v: TextView, timerValue: Long) {
        v.text = timerValue.toString()
    }

    @JvmStatic
    @BindingAdapter("itemDecoratorDrawable")
    fun setItemDecoration(v: RecyclerView, drawable: Drawable) {
        if (v.itemDecorationCount > 0) {
            for (i in 0..v.itemDecorationCount) {
                v.removeItemDecorationAt(i)
            }
        }
        val decorator = DividerItemDecoration(drawable)
        v.addItemDecoration(decorator)
    }

    @JvmStatic
    @BindingAdapter("itemAnimator")
    fun setItemAnimator(v: RecyclerView, animator: RecyclerView.ItemAnimator) {
        v.itemAnimator = animator
    }
}