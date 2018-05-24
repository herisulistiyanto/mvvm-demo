package com.andro.indieschool.mymvvmdemo.base.custom.glide

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class GlideImageLoader(private val imageView: ImageView?, private val progressBar: ProgressBar?) {

    fun load(url: String?, options: RequestOptions?) {
        if (url == null || options == null) return

        onConnecting()

        //set Listener & start
        ProgressAppGlideModule.expect(url, object : ProgressAppGlideModule.UIonProgressListener {
            override fun onProgress(bytesRead: Long, expectedLength: Long) {
                progressBar?.progress = (100 * bytesRead / expectedLength).toInt()
            }

            override fun getGranualityPercentage(): Float {
                return 1.0f
            }
        })
        //Get Image
        imageView?.let {
            Glide.with(it.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(options.skipMemoryCache(true))
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?,
                                                  target: Target<Drawable>?,
                                                  isFirstResource: Boolean): Boolean {
                            ProgressAppGlideModule.forget(url)
                            onFinished()
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?,
                                                     target: Target<Drawable>?, dataSource: DataSource?,
                                                     isFirstResource: Boolean): Boolean {
                            ProgressAppGlideModule.forget(url)
                            onFinished()
                            return false
                        }
                    })
                    .into(it)
        }
    }


    private fun onConnecting() {
        progressBar?.visibility = View.VISIBLE
    }

    private fun onFinished() {
        progressBar?.visibility = View.GONE
        imageView?.visibility = View.VISIBLE
    }

}