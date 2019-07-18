package com.wellsen.algolia.android.data.remote.api

import com.wellsen.algolia.android.data.remote.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
interface AlgoliaApi {

    @GET("search")
    fun fetchNews(@Query("query") query: String): Single<NewsResponse>

}
