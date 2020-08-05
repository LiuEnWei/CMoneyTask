package com.wayne.cmoneytask.model.imageloader

import android.content.ComponentCallbacks2
import android.graphics.Bitmap
import android.util.LruCache

class BitmapCache {

    private lateinit var bitmapCache: LruCache<String, Bitmap>

//    companion object {
//        val instance: BitmapCache by lazy { BitmapCache() }
//    }

    init {
        getLruCache()
    }

    private fun getLruCache() {
        val cacheSize = (Runtime.getRuntime().maxMemory() / 8).toInt()
        bitmapCache = object: LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount
            }
        }
    }

    fun addBitmap(url: String, bitmap: Bitmap) {
        if (bitmapCache.get(url) == null) {
            bitmapCache.put(url, bitmap)
        }
    }

    fun getBitmap(url: String): Bitmap? {
        return bitmapCache.get(url)
    }

    fun removeBitmap(url: String) {
        bitmapCache.remove(url)
    }

    fun trimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_BACKGROUND) {
            bitmapCache.evictAll()
        } else if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            bitmapCache.trimToSize(bitmapCache.maxSize() / 2)
        }
    }

    fun clear() {
        bitmapCache.evictAll()
    }
}