package com.wellsen.algolia.android.ui.main

import android.view.View
import com.wellsen.algolia.android.data.remote.api.AlgoliaApi
import com.wellsen.algolia.android.data.remote.response.News
import com.wellsen.algolia.android.data.remote.response.NewsResponse
import com.wellsen.algolia.android.ui.BaseViewModel
import com.wellsen.algolia.android.util.NonNullMutableLiveData
import com.wellsen.algolia.android.util.extension.with
import timber.log.Timber

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
class MainViewModel(
    private val algoliaApi: AlgoliaApi
) : BaseViewModel() {

    var loadingVisibility: NonNullMutableLiveData<Int> = NonNullMutableLiveData(View.INVISIBLE)
    var news: NonNullMutableLiveData<List<News>> = NonNullMutableLiveData(mutableListOf())

    fun fetchNews(query: String) {
        @Suppress("UnstableApiUsage")
        add(algoliaApi.fetchNews(query).with()
            .doOnSubscribe { onFetchNewsStart() }
            .doOnTerminate { onFetchNewsFinish() }
            .subscribe(
                { onFetchNewsSuccess(it) },
                { onFetchNewsError(it) }
            ))
    }

    private fun onFetchNewsStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onFetchNewsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onFetchNewsSuccess(response: NewsResponse) {
        this.news.value = response.hits
    }

    private fun onFetchNewsError(t: Throwable) {
        Timber.e(t)
    }

}
