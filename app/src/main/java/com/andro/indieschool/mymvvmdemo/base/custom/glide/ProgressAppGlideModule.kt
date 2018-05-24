package com.andro.indieschool.mymvvmdemo.base.custom.glide

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okio.*
import java.io.IOException
import java.util.*


@GlideModule
class ProgressAppGlideModule: AppGlideModule() {

    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        super.registerComponents(context, glide, registry)
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor {
                    val request = it.request()
                    val response = it.proceed(request)
                    val listener = DispatchingProgressListener()
                    return@addNetworkInterceptor response.newBuilder()
                            .body(response.body()?.let { body -> OkHttpProgressResponseBody(request.url(), body, listener) })
                            .build()
                }
    }

    private interface ResponseProgressListener {
        fun update(url: HttpUrl, bytesRead: Long, contentLength: Long)
    }

    interface UIonProgressListener {
        /**
         * Control how often the listener needs an update. 0% and 100% will always be dispatched.
         * @return in percentage (0.2 = call [.onProgress] around every 0.2 percent of progress)
         */
        fun getGranualityPercentage(): Float

        fun onProgress(bytesRead: Long, expectedLength: Long)
    }

    companion object {
        fun forget(url: String) {
            ProgressAppGlideModule.DispatchingProgressListener.forget(url)
        }

        fun expect(url: String, listener: ProgressAppGlideModule.UIonProgressListener) {
            ProgressAppGlideModule.DispatchingProgressListener.expect(url, listener)
        }
    }

    private class DispatchingProgressListener internal constructor() : ProgressAppGlideModule.ResponseProgressListener {

        private val handler: Handler = Handler(Looper.getMainLooper())

        override fun update(url: HttpUrl, bytesRead: Long, contentLength: Long) {
            val key = url.toString()
            val listener = LISTENERS[key] ?: return
            if (contentLength <= bytesRead) forget(key)
            if (needsDispatch(key, bytesRead, contentLength, listener.getGranualityPercentage())) {
                handler.post{
                    listener.onProgress(bytesRead, contentLength)
                }
            }
        }

        private fun needsDispatch(key: String, current: Long, total: Long, granularity: Float): Boolean {
            if (granularity == 0f || current == 0L || total == current) {
                return true
            }
            val percent = 100f * current / total
            val currentProgress = (percent / granularity).toLong()
            val lastProgress = PROGRESSES.get(key)
            return if (lastProgress == null || currentProgress != lastProgress) {
                PROGRESSES[key] = currentProgress
                true
            } else {
                false
            }
        }

        companion object {
            private val LISTENERS = WeakHashMap<String, UIonProgressListener>()
            private val PROGRESSES = WeakHashMap<String, Long>()

            internal fun forget(url: String) {
                LISTENERS.remove(url)
                PROGRESSES.remove(url)
            }

            internal fun expect(url: String, listener: UIonProgressListener) {
                LISTENERS[url] = listener
            }
        }
    }

    private class OkHttpProgressResponseBody internal constructor(private val url: HttpUrl, private val responseBody: ResponseBody,
                                                                  private val progressListener: ResponseProgressListener) : ResponseBody() {
        private var bufferedSource: BufferedSource? = null

        override fun contentType(): MediaType? {
            return responseBody.contentType()
        }

        override fun contentLength(): Long {
            return responseBody.contentLength()
        }

        override fun source(): BufferedSource? {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()))
            }
            return bufferedSource
        }

        private fun source(source: Source): Source {
            return object : ForwardingSource(source) {
                internal var totalBytesRead = 0L

                @Throws(IOException::class)
                override fun read(sink: Buffer, byteCount: Long): Long {
                    val bytesRead = super.read(sink, byteCount)
                    val fullLength = responseBody.contentLength()
                    if (bytesRead.equals(-1)) { // this source is exhausted
                        totalBytesRead = fullLength
                    } else {
                        totalBytesRead += bytesRead
                    }
                    progressListener.update(url, totalBytesRead, fullLength)
                    return bytesRead
                }
            }
        }
    }
}