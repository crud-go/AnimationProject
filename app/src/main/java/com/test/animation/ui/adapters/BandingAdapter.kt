package com.test.animation.ui.adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BandingAdapter {
    @BindingAdapter("imageUrl","error")
    @JvmStatic
    fun loadImage(view: ImageView, url: String, error: Drawable) {
        Glide.with(view.context).load(url).error(error).into(view)
    }



}