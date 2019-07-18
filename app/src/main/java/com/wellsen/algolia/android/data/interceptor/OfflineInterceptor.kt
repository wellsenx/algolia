package com.wellsen.algolia.android.data.interceptor

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */

const val ONE_WEEK = 60 * 60 * 24 * 7

class OfflineInterceptor(private val application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val cacheControl = if (isNetworkConnected(application)) {
            "public, max-age=" + 15
        } else {
            "public, only-if-cached, max-stale=$ONE_WEEK"
        }

        requestBuilder.header("Cache-Control", cacheControl)

        return chain.proceed(requestBuilder.build())
    }

}

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

    return activeNetwork?.isConnected == true
}
