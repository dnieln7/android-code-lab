package com.dnieln7.data_binding

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import kotlin.math.roundToInt

object BindingAdapters {

    @BindingAdapter("app:hideIfZero")
    fun hideIfZero(view: View, number: Int) {
        view.visibility = if (number == 0) View.GONE else View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter(value = ["app:progressScaled", "app:max"], requireAll = true)
    fun progressScaled(progressBar: ProgressBar, likes: Int, max: Int) {
        progressBar.progress = if (max == 0) 50 else ((likes.toDouble() / max.toDouble()) * 100.0).roundToInt()
    }
}