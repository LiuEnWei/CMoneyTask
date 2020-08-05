package com.wayne.cmoneytask.utils

import android.accounts.NetworkErrorException
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {

    private const val REQUEST_METHOD_GET = "GET"
    private const val READ_TIMEOUT = 5000
    private const val CONNECT_TIMEOUT = 5000

    // GET
    suspend fun get(url: String): String {
        return withContext(Dispatchers.IO) {
            var conn: HttpURLConnection? = null
            try {
                conn = URL(url).openConnection() as HttpURLConnection
                conn.requestMethod = REQUEST_METHOD_GET
                conn.readTimeout = READ_TIMEOUT
                conn.connectTimeout = CONNECT_TIMEOUT

                return@withContext when (val responseCode = conn.responseCode) {
                    HttpURLConnection.HTTP_OK -> {
                        conn.inputStream.bufferedReader().readText()
                    }

                    else -> {
                        throw NetworkErrorException("Response Code : $responseCode")
                    }
                }
            } finally {
                conn?.disconnect()
            }
        }
    }

    suspend fun downloadBitmap(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            var conn: HttpURLConnection? = null
            var bitmap: Bitmap? = null
            try {
                conn = URL(url).openConnection() as HttpURLConnection
                conn.setRequestProperty("User-Agent", "chrome")
//                conn.connect()
                bitmap = BitmapFactory.decodeStream(conn.inputStream)

                Log.e("NetworkUtils", "downloadBitmap : $bitmap")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                conn?.disconnect()
            }
            return@withContext bitmap
        }
    }

    // POST, PUT, DELETE...etc function, this task just need GET
}