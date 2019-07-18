package com.wellsen.algolia.android.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wellsen.algolia.android.data.remote.response.News

/**
 * Project algolia.
 *
 * Created by wellsen on 2019-07-18.
 */
@Suppress("unused")
@BindingAdapter(value = ["news", "viewModel"])
fun setNews(view: RecyclerView, news: List<News>, vm: MainViewModel) {

    view.adapter?.run {
        if (this is NewsAdapter) {
            this.news = news
            this.notifyDataSetChanged()
        }
    } ?: run {
        NewsAdapter(news, vm).apply { view.adapter = this }
    }

}