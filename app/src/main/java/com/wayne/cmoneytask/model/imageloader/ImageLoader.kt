package com.wayne.cmoneytask.model.imageloader

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import android.widget.ImageView
import androidx.lifecycle.LifecycleCoroutineScope
import com.wayne.cmoneytask.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageLoader private constructor(context: Context?): ComponentCallbacks2 {

    private val bitmapCache = BitmapCache()

    companion object {
        @Volatile private var imageLoader: ImageLoader? = null

        fun with(context: Context?): ImageLoader {
            if (imageLoader == null) {
                synchronized(this) {
                    imageLoader = ImageLoader(context)
                }
            }
            return imageLoader!!
        }
    }

    init {
        context?.applicationContext?.registerComponentCallbacks(imageLoader)
    }

    fun displayImage(url: String, lifecycleScope: LifecycleCoroutineScope, imgView: ImageView) {
        imgView.tag = url
        bitmapCache.getBitmap(url)?.let {
            imgView.setImageBitmap(it)
            return@displayImage
        }

        lifecycleScope.launch(Dispatchers.Main) {
            val bitmap = NetworkUtils.downloadBitmap(url)
            bitmap?.let {
                bitmapCache.addBitmap(url, it)
                if (imgView.tag == url) {
                    imgView.setImageBitmap(it)
                }
            }
        }
    }

    override fun onLowMemory() {
        bitmapCache.clear()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        // Do nothing.
    }

    override fun onTrimMemory(level: Int) {
        bitmapCache.trimMemory(level)
    }
}