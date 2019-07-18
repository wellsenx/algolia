package com.wellsen.algolia.android.data.interceptor

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
class OfflineInterceptor(private val application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (!isNetworkConnected(application)) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }

        return chain.proceed(builder.build())
    }

}

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

    return activeNetwork?.isConnected == true
}
