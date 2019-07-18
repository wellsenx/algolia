package com.wellsen.algolia.android.module

import com.google.gson.GsonBuilder
import com.wellsen.algolia.android.BuildConfig
import com.wellsen.algolia.android.data.interceptor.OfflineInterceptor
import com.wellsen.algolia.android.data.remote.api.AlgoliaApi
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */

const val cacheSize: Long = 10 * 1024 * 1024  // 10 MB

val networkModule = module {

    single { GsonBuilder().create() }

    single { OfflineInterceptor(androidApplication()) }

    single {
        OkHttpClient.Builder()
            .apply {
                connectTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
                readTimeout(10, TimeUnit.SECONDS)
                cache(Cache(androidApplication().cacheDir, cacheSize))
                addInterceptor(get<OfflineInterceptor>())
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL + "v1/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(get())
            .build()
            .create(AlgoliaApi::class.java)
    }

}
