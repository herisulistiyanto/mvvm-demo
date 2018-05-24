package com.andro.indieschool.mymvvmdemo.base.extension

import android.support.annotation.DrawableRes
import android.widget.ImageView
import android.widget.ProgressBar
import com.andro.indieschool.mymvvmdemo.base.custom.glide.GlideImageLoader
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrlWithPlaceholder(imageUrl: String?,
                                         progressBar: ProgressBar?,
                                         @DrawableRes placeholder: Int,
                                         @DrawableRes errorPlaceholder: Int) {
    val options = RequestOptions()
            .centerCrop()
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .error(errorPlaceholder)
            .priority(Priority.IMMEDIATE)

    GlideImageLoader(this, progressBar)
            .load(imageUrl, options)
}