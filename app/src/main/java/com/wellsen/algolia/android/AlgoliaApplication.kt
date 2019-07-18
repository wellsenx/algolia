package com.wellsen.algolia.android

import android.app.Application
import com.wellsen.algolia.android.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
@Suppress("unused")
class AlgoliaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@AlgoliaApplication)
            if (BuildConfig.DEBUG) {
                printLogger()
            }
            modules(
                listOf(
                    networkModule
                )
            )
        }

    }
}